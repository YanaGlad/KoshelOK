package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.WalletData

class WalletsViewModel : ViewModel() {
    var walletList : MutableLiveData<ArrayList<WalletData>> = MutableLiveData<ArrayList<WalletData>>()

}