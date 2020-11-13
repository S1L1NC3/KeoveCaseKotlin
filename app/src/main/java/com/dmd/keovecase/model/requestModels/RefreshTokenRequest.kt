package com.dmd.keovecase.model.requestModels

class RefreshTokenRequest(refreshToken: String): BaseRequest() {
    private var refreshToken: String = ""

    init {
        this.refreshToken = refreshToken
    }
}