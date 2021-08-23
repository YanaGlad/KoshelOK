package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData

class WalletsViewModel : ViewModel() {
    var walletList: MutableLiveData<ArrayList<WalletData>> =
        MutableLiveData<ArrayList<WalletData>>()

    var hiddenWalletList: MutableLiveData<ArrayList<WalletData>> =
        MutableLiveData<ArrayList<WalletData>>()

    init {
        walletList.value = ArrayList()
        hiddenWalletList.value = ArrayList()

        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                "12323",
                "13123",
                Currency(id = 555,"weq", "dollar")
            )
        )
        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                "12323",
                "13123",
                Currency(id = 555,"weq", "dollar")
            )
        )
        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                "12323",
                "13123",
                Currency(id = 555,"weq", "dollar")
            )
        )
        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                "12323",
                "13123",
                Currency(id = 555,"weq", "dollar")
            )
        )
        walletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 1",
                "12323",
                "13123",
                Currency(id = 555,"weq", "dollar")
            )
        )
        hiddenWalletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 9",
                "12323",
                "200000",
                Currency(id = 555,"weq", "euro")
            )
        )

        hiddenWalletList.value!!.add(
            WalletData(
                1020,
                1313,
                "My wallet",
                "12323",
                "121100",
                Currency(id = 555,"weq", "euro")
            )
        )
    }
}