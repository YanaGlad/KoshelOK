package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.repository.CategoryRepository
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(val repository: CategoryRepository) : ViewModel()  {

    companion object {
        const val TEMP_USERNAME = "test-tincoffJJ"
        var TEMP_WALLET_ID = 1
    }

    init {

    }

    private val _viewState: MutableLiveData<WalletListViewState> = MutableLiveData()
    val viewState: LiveData<WalletListViewState>
        get() = _viewState


    fun addCategory(categoryDataSample: CategoryDataSample) {
        repository.createCategory( categoryDataSample)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete {
                _viewState.postValue(WalletListViewState.SuccessOperation)
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }
}