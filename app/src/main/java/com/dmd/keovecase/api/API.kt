package com.dmd.keovecase.api

import com.dmd.keovecase.model.AuthenticateResponse
import com.dmd.keovecase.model.Weather
import com.dmd.keovecase.model.requestModels.LoginRequest
import com.dmd.keovecase.model.requestModels.RefreshTokenRequest
import com.dmd.keovecase.model.requestModels.RevokeRefreshTokenRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    @POST("api/user/authenticate")
    fun login(@Body body: LoginRequest): Single<AuthenticateResponse>

    @POST("api/user/refresh-token")
    fun refreshToken(@Body body: RefreshTokenRequest): Single<AuthenticateResponse>

    @POST("api/user/revoke-refresh-token")
    fun revokeRefreshToken(@Body body: RevokeRefreshTokenRequest): Single<AuthenticateResponse>

    @GET("weatherforecast")
    fun getWeather(@Header("Authorization") value: String) : Single<ArrayList<Weather>>
}