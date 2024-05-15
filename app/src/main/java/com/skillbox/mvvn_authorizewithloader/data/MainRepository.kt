package com.skillbox.mvvn_authorizewithloader.data

import com.skillbox.mvvn_authorizewithloader.entity.UserInfo
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource
) {

    suspend fun authorize(login: String, password: String): Long {
        return 1L
    }

    suspend fun getUserInfo(userId: Long): UserInfo {
        return userInfoDataSource.loadUserInfo(userId)
    }
}