package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.repository.CategoryRepository
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject constructor(val repository: CategoryRepository) :
    ViewModel() {

    private val _viewState: MutableLiveData<CategoryListViewState> = MutableLiveData()
    val viewState: LiveData<CategoryListViewState>
        get() = _viewState

    fun getCategoryList(income : Boolean) {
        _viewState.value = CategoryListViewState.Loading
        val disposable = repository.getCategories(income)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                _viewState.postValue(it)
            }
    }

    fun loadCategories(context : Context) {
        val disposable = repository.loadCategories(context)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {viewState ->
                    _viewState.postValue(viewState)
                },
                {}
            )
    }

    fun addCategory(categoryDataSample: CategoryDataSample) {
        repository.createCategory(categoryDataSample)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete {
                _viewState.postValue(CategoryListViewState.SuccessOperation)
            }
            .doOnError {
                _viewState.postValue(CategoryListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }
}
