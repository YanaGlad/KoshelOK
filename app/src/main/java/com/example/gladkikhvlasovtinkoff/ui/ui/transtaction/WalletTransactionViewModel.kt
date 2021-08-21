package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel

class WalletTransactionViewModel : ViewModel() {
    var transactionList : MutableLiveData<ArrayList<WalletTransactionModel>> = MutableLiveData<ArrayList<WalletTransactionModel>>()

    //получение списка из бд

    init {

        val list = ArrayList<WalletTransactionModel>()

//        list.add(
//            WalletTransactionModel(
//                System.currentTimeMillis(),
//                R.drawable.ic_capitalisation,
//                R.string.supermarket,
//                "Траты",
//                "-12000"
//            )
//        )
//
//        list.add(
//            WalletTransactionModel(
//                System.currentTimeMillis() - MILLIS_IN_DAY,
//                R.drawable.ic_salary,
//                R.string.salary,
//                "Пополнение",
//                "130000"
//            )
//        )
        transactionList.value = list
    }
}