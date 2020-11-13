package com.dmd.keovecase.api

import com.dmd.keovecase.model.AuthenticateResponse
import com.dmd.keovecase.model.Weather
import com.dmd.keovecase.model.requestModels.LoginRequest
import com.dmd.keovecase.model.requestModels.RefreshTokenRequest
import com.dmd.keovecase.model.requestModels.RevokeRefreshTokenRequest
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class Service{
    private val targetDest = "http://testcase1.keove.com/" //ismini lint sorgusunda warning çıkmasın diye değiştirdim

    private val api = Retrofit.Builder()
        .baseUrl(targetDest)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(API::class.java)

    fun login(loginRequest: LoginRequest) : Single<AuthenticateResponse> {
        return api.login(loginRequest)
    }

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest) : Single<AuthenticateResponse> {
        return api.refreshToken(refreshTokenRequest)
    }

    fun revokeRefreshToken(revokeRefreshTokenRequest: RevokeRefreshTokenRequest) : Single<AuthenticateResponse> {
        return api.revokeRefreshToken(revokeRefreshTokenRequest)
    }

    fun weatherInfo(bearerToken: String) : Single<ArrayList<Weather>> {
        return api.getWeather("Bearer $bearerToken")
    }

}