package com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.extension.MILLIS_IN_DAY

class WalletOperationViewModel : ViewModel() {
    var transactionList : MutableLiveData<ArrayList<WalletOperationData>> = MutableLiveData<ArrayList<WalletOperationData>>()

    //получение списка из бд

    init {

        val list = ArrayList<WalletOperationData>()

        list.add(
            WalletOperationData(
                System.currentTimeMillis(),
                R.drawable.ic_capitalisation,
                R.string.supermarket,
                "Траты",
                "-12000"
            )
        )

        list.add(
            WalletOperationData(
                System.currentTimeMillis() - MILLIS_IN_DAY,
                R.drawable.ic_salary,
                R.string.salary,
                "Пополнение",
                "130000"
            )
        )
        transactionList.value = list
    }
}