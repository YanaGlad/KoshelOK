package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.LocalAuthProvider
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val remoteWalletDataProvider: RemoteWalletDataProvider,
    val localAuthDataProvider: LocalAuthProvider,
    val authDataHolder: AuthDataHolder
) : AuthRepository {

    override fun logInWithAccount(account: GoogleSignInAccount): Completable =
        Completable.create { emitter ->
            if (authDataHolder.isAuth()) {
                if (authDataHolder.isUserChangeAccount(account)) {
                    authDataHolder.clearUserData()
                    localAuthDataProvider.clearAllTables()
                    checkAndAddAccount(account, emitter)
                } else
                    emitter.onComplete()
            }else{
                checkAndAddAccount(account, emitter)
            }
        }

    private fun signUpWithAccount(account: GoogleSignInAccount): Completable =
        Completable.create { emitter ->
            try {
                remoteWalletDataProvider.addUserWithAccount(account)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .doOnSuccess{
                        authDataHolder.parseAccountInfo(account)
                        if (authDataHolder.isAuth()) {
                            emitter.onComplete()
                        } else
                            emitter.onError(IllegalArgumentException("Wrong account info"))
                    }
                    .doOnError{
                        emitter.onError(it)
                    }
                    .subscribe()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    private fun findUserByUsername(username: String) =
        remoteWalletDataProvider.findUserByUsername(username)


    private fun checkAndAddAccount(account: GoogleSignInAccount, emitter : CompletableEmitter) =
        findUserByUsername(
            authDataHolder.getAccountKey(account)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSuccess { users ->
                when {
                    users.isEmpty() -> {
                        signUpWithAccount(account)
                            .observeOn(Schedulers.io())
                            .subscribeOn(Schedulers.io())
                            .doOnComplete {
                                emitter.onComplete()
                            }
                            .doOnError{
                                emitter.onError(it)
                            }
                            .subscribe()
                    }
                    users.isNotEmpty() ->
                    {
                        authDataHolder.parseAccountInfo(account)
                        emitter.onComplete()
                    }
                }
            }
            .doOnError{
                it.printStackTrace()
            }
            .subscribe()
}