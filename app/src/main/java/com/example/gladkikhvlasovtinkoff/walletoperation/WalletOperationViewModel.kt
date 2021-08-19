package com.example.gladkikhvlasovtinkoff.walletoperation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletOperationViewModel : ViewModel() {
    var listEdit: MutableLiveData<ArrayList<EditModel>> = MutableLiveData<ArrayList<EditModel>>()

}