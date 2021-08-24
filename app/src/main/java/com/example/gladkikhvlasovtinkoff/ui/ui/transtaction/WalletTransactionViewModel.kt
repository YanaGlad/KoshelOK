package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.extension.MILLIS_IN_DAY
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WalletTransactionViewModel@Inject constructor(val repository: TransactionRepository) : ViewModel() {
    var transactionList: MutableList<WalletTransactionModel> =
        mutableListOf()

    companion object {
        const val TEMP_USER_ID = 1L
        const val TEMP_WALLET_ID = 1L
    }

    private val _viewState: MutableLiveData<TransactionListViewState> = MutableLiveData()
    val viewState: LiveData<TransactionListViewState>
        get() = _viewState

    fun getTransactionListByWalletId(){
        repository.getAllTransactionsByWalletId(TEMP_WALLET_ID)
            .doOnNext {  viewState ->
                if(viewState!=null)
                    _viewState.postValue(viewState)

            }
            .doOnError {
                _viewState.postValue(TransactionListViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

    init {
        getTransactionListByWalletId()
    }

//        val list = ArrayList<WalletTransactionModel>()
//
//        list.apply {
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis(),
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis(),
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis(),
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis(),
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY,
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 2,
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 3,
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 4,
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome =
//                    false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 5,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    walletId = 0,
//                    isIncome = false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//            add(
//                WalletTransactionModel(
//                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 6,
//                    walletId = 0,
//                    transactionCategoryData =
//                    TransactionCategoryData(
//                        name = "chototam",
//                        iconId = R.drawable.ic_gas_station,
//                        id = 5,
//                        description = "",
//                        color = R.color.default_dot_color
//                    ),
//                    isIncome = false,
//                    amount = "isgjsiog",
//                    currency = Currency(
//                        id = 555,
//                        code = "au",
//                        name = "rubli"
//                    )
//                )
//            )
//        }
//        transactionList = list
   // }
}