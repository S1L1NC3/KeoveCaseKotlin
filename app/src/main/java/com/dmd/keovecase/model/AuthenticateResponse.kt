package com.dmd.keovecase.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthenticateResponse (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("surname") val surname : String,
    @SerializedName("username") val username : String,
    @SerializedName("jwtToken") val jwtToken : String,
    @SerializedName("refreshToken") val refreshToken : String
) : Serializable