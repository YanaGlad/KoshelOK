package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.LocalAuthProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.ui.ui.welcome.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localAuthDataProvider: LocalAuthProvider,
    private val authDataHolder: AuthDataHolder
) : AuthRepository {

    override fun logInWithAccount(account: GoogleSignInAccount): Single<AuthViewState> =
        Single.create { emitter ->
            if (authDataHolder.isAuth()) {
                if (authDataHolder.isUserChangeAccount(account)) {
                    authDataHolder.clearUserData()
                    localAuthDataProvider.clearAllTables()
                    val findUserDisp = checkAndAddAccount(account, emitter)
                }
            } else {
                val findUserDisp = checkAndAddAccount(account, emitter)
            }
        }


    private fun findUserByUsername(username: String) =
        remoteWalletDataProvider.findUserByUsername(username)


    private fun checkAndAddAccount(
        account: GoogleSignInAccount,
        emitter: SingleEmitter<AuthViewState>
    ) =
        findUserByUsername(authDataHolder.getAccountKey(account))
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { users ->
                    when {
                        users.isEmpty() -> {
                            remoteWalletDataProvider.addUserWithAccount(account)
                                .observeOn(Schedulers.io())
                                .subscribeOn(Schedulers.io())
                                .subscribe(
                                    {
                                        emitter.onSuccess(AuthViewState.SuccessLogin)
                                    },
                                    {
                                        emitter.onSuccess(it.convertToViewState())
                                        it.printStackTrace()
                                    }
                                )
                        }
                        users.isNotEmpty() -> {
                            authDataHolder.parseAccountInfo(account)
                            emitter.onSuccess(AuthViewState.SuccessLogin)
                        }
                    }
                },
                {
                    emitter.onSuccess(it.convertToViewState())
                }
            )


    private fun signUpWithAccount(account: GoogleSignInAccount): Completable =
        Completable.create { emitter ->
            remoteWalletDataProvider.addUserWithAccount(account)
                .doOnSuccess {
                    authDataHolder.parseAccountInfo(account)
                    emitter.onComplete()
                }
        }

    private fun Throwable.convertToViewState(): AuthViewState =
        when (this) {
            is IOException -> AuthViewState.Error.NetworkError
            else -> AuthViewState.Error.UnexpectedError
        }
}