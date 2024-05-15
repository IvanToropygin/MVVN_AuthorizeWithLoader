package com.skillbox.mvvn_authorizewithloader.domain

import com.skillbox.mvvn_authorizewithloader.data.LocalStorageRepository
import com.skillbox.mvvn_authorizewithloader.data.MainRepository
import com.skillbox.mvvn_authorizewithloader.entity.UserInfo

class GetUserInfoUseCase(
    private val mainRepository: MainRepository,
    private val localStorageRepository: LocalStorageRepository
) {

    suspend fun getUserInfo(login: String, password: String): UserInfo {
        val userId = mainRepository.authorize(login, password)
        val userInfo = mainRepository.getUserInfo(userId)
        localStorageRepository.saveUserInfo(userInfo)
        return userInfo
    }
}