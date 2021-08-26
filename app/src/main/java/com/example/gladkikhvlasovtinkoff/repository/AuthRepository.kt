package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.ui.ui.welcome.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single

interface AuthRepository {
    fun logInWithAccount(account : GoogleSignInAccount) : Single<AuthViewState>
}