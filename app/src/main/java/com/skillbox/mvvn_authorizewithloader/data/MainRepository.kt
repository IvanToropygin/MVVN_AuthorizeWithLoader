package com.skillbox.mvvn_authorizewithloader.data

import com.skillbox.mvvn_authorizewithloader.entity.UserInfo

class MainRepository(
    private val userInfoDataSource: UserInfoDataSource
) {

    private var count = 0

    suspend fun authorize(login: String, password: String): Long {
        return 1L
    }

    suspend fun getUserInfo(userId: Long): UserInfo {
        return userInfoDataSource.loadUserInfo(userId)
    }
}