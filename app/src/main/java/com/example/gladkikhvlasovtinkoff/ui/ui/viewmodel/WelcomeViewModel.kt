package com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.repository.AuthRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject constructor(val authRepository: AuthRepository) : ViewModel() {
    private val _viewState: MutableLiveData<AuthViewState> = MutableLiveData()
    val viewState: LiveData<AuthViewState>
        get() = _viewState


    fun logInWithAccount(account: GoogleSignInAccount) {
        // TODO viewmodel не должна быть зависима от GoogleSignInAccount, нужен mapping в локальную сущность
        _viewState.value = AuthViewState.Loading
        // TODO нужно обработать disposable
        val disposable = authRepository.logInWithAccount(
            account
        )
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { viewState ->
                    _viewState.postValue(viewState)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}
