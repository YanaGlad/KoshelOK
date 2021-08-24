package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmCreatingViewModel @Inject constructor(
    val repository: TransactionRepository
) : ViewModel() {

    private val _viewState: MutableLiveData<ConfirmCreatingViewState> = MutableLiveData()
    val viewState: LiveData<ConfirmCreatingViewState>
        get() = _viewState


}