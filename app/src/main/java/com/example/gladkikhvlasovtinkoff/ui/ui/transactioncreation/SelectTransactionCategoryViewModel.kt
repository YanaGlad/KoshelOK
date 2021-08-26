package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.repository.CategoryRepository
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SelectTransactionCategoryViewModel @Inject constructor(val repository: CategoryRepository) :
    ViewModel() {

    private val _viewState: MutableLiveData<CategoryListViewState> = MutableLiveData()
    val viewState: LiveData<CategoryListViewState>
        get() = _viewState

    init {
        getCategoryList()
        loadCategories()
    }

    fun getCategoryList() {
        val disposable = repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                _viewState.postValue(it)
            }
    }

    fun loadCategories() {
        repository.loadCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {viewState ->
                    _viewState.postValue(viewState)
                },
                {}
            )
    }

}