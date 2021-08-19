package com.example.gladkikhvlasovtinkoff.walletoperation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.extension.MILLIS_IN_DAY
import com.example.gladkikhvlasovtinkoff.model.WalletOperationBuilder

class WalletOperationViewModel : ViewModel() {
    var transactionList : MutableLiveData<ArrayList<WalletOperationModel>> = MutableLiveData<ArrayList<WalletOperationModel>>()

    init {

        val list = ArrayList<WalletOperationModel>()

        list.add(
            WalletOperationModel(
                System.currentTimeMillis(),
                R.drawable.ic_capitalisation,
                R.string.supermarket,
                "Траты",
                "-12000"
            )
        )

        list.add(
            WalletOperationModel(
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