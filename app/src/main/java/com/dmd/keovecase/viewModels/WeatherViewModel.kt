package com.dmd.keovecase.viewModels

import androidx.lifecycle.MutableLiveData
import com.dmd.keovecase.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : BaseViewModel() {
    private var bearerToken = ""
    val data = MutableLiveData<ArrayList<Weather>>()

    override fun refreshData() {
        getDataFromApi()
    }

    fun setBearer(value: String){
        this.bearerToken = value
    }

    private fun getDataFromApi(){
        dataLoading.value= true

        disposable.add(
            dataApiService.weatherInfo(bearerToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Weather>>(){
                    override fun onSuccess(t: ArrayList<Weather>) {
                        data.value = t
                        dataError.value=false
                        dataLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        dataLoading.value=false
                        dataError.value=true
                        e.printStackTrace()

                    }

                })
        )
    }
}