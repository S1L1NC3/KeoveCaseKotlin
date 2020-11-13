package com.dmd.keovecase.model.requestModels

class RevokeRefreshTokenRequest(refreshToken: String): BaseRequest() {
    private var refreshToken: String = ""

    init {
        this.refreshToken = refreshToken
    }
}