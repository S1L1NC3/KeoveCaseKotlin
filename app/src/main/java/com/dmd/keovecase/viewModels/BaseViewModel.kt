package com.dmd.keovecase.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmd.keovecase.api.Service
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel(){
    protected val dataApiService = Service()
    protected val disposable = CompositeDisposable()

    val dataError = MutableLiveData<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()

    open fun refreshData(){
    }


}