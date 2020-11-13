package com.dmd.keovecase.viewModels

import androidx.lifecycle.MutableLiveData
import com.dmd.keovecase.model.AuthenticateResponse
import com.dmd.keovecase.model.requestModels.LoginRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {
    private lateinit var loginRequest : LoginRequest
    val data = MutableLiveData<AuthenticateResponse>()

    fun assignData(loginRequest: LoginRequest){
        this.loginRequest = loginRequest
    }

    override fun refreshData(){
        loginUsingApi()
    }

    private fun loginUsingApi(){
        dataLoading.value= true

        disposable.add(
            dataApiService.login(loginRequest = loginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AuthenticateResponse>(){
                    override fun onSuccess(t: AuthenticateResponse) {
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