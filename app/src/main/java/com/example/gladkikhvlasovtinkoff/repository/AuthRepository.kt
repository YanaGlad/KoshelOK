package com.example.gladkikhvlasovtinkoff.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable

interface AuthRepository {
    fun logInWithAccount(account : GoogleSignInAccount) : Completable
}