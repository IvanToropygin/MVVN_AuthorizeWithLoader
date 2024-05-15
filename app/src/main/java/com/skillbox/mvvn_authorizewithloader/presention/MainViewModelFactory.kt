package com.skillbox.mvvn_authorizewithloader.presention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skillbox.mvvn_authorizewithloader.data.LocalStorageRepository
import com.skillbox.mvvn_authorizewithloader.data.MainRepository
import com.skillbox.mvvn_authorizewithloader.data.UserInfoDataSource
import com.skillbox.mvvn_authorizewithloader.domain.GetUserInfoUseCase

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val userInfoDataSource = UserInfoDataSource()
            val mainRepository = MainRepository(userInfoDataSource)
            val localRepository = LocalStorageRepository()
            val userInfoUseCase = GetUserInfoUseCase(mainRepository, localRepository)
            return MainViewModel(userInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}