package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single

interface AuthRepository {
    // TODO GoogleSignInAccount не должен передаваться через repository, нужна локальная сущность или обертка вокруг, т.к. нарушает инкапсуляцию архитектурных слоев
    // делает AuthRepository зависимым от фреймворка гугла, т.е. от самых нижних слоев
    fun logInWithAccount(account : GoogleSignInAccount) : Single<AuthViewState>

    fun logOut()
}
