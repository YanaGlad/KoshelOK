package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData

class WalletsViewModel : ViewModel() {
    var walletList: MutableLiveData<ArrayList<WalletData>> =
        MutableLiveData<ArrayList<WalletData>>()

    init {
        walletList.value = ArrayList()

        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                12323,
                "13123",
                Currency("weq", "dollar")
            )
        )
    }
}