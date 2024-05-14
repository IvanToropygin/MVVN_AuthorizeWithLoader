package com.skillbox.mvvn_authorizewithloader

sealed class State {
    object Loading: State()
    object Success: State()

    data class Error(
        val loginError: String?,
        val passwordError: String?
        ): State()
}