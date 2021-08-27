package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.dataprovider.LocalAuthProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
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

    // TODO отрефакторить без Single.create
    override fun logInWithAccount(account: GoogleSignInAccount): Single<AuthViewState> =
        Single.create { emitter ->
            if (authDataHolder.isAuth()) {
                // TODO по хорошему нужно вынести в отдельную функцию
                if (authDataHolder.isUserChangeAccount(account)){
                    authDataHolder.clearUserData()
                    localAuthDataProvider.clearAllTables()
                    val findUserDisp = checkAndAddAccount(account, emitter)
                }
                else {
                    emitter.onSuccess(AuthViewState.SuccessLogin)
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
                }, {
                    emitter.onSuccess(it.convertToViewState())
                }
            )

    private fun Throwable.convertToViewState(): AuthViewState =
        when (this) {
            is IOException -> AuthViewState.Error.NetworkError
            else -> AuthViewState.Error.UnexpectedError
        }
}
