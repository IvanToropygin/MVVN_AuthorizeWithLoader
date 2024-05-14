package com.skillbox.mvvn_authorizewithloader

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init")
    }

    fun onClickMeButtonClick(login: String, password: String) {
        Log.d(TAG, "onClickMeButtonClick")
        viewModelScope.launch {
            val isLoginEmpty = login.isBlank()
            val isPasswordEmpty = password.isBlank()

            if (isLoginEmpty || isPasswordEmpty) {

                var loginError: String? = null
                if (isLoginEmpty) {
                    loginError = "login is required" }

                var passwordError: String? = null
                if (isPasswordEmpty) {
                    passwordError = "password is required" }

                _state.value = State.Error(loginError, passwordError)
                _error.send("login or password is empty")
            } else {
                _state.value = State.Loading
                try {
                    repository.getData()
                    _state.value = State.Success
                } catch (e: Exception) {
                    _error.send(e.toString())
                    _state.value = State.Error(null, null)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }
}