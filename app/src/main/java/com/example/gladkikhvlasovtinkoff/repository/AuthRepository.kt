package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {
    fun logInWithAccount(account : GoogleSignInAccount) : Completable
}