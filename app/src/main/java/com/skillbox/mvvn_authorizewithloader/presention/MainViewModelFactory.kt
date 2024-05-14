package com.skillbox.mvvn_authorizewithloader.presention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skillbox.mvvn_authorizewithloader.data.MainRepository

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}