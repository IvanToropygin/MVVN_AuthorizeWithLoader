package com.skillbox.mvvn_authorizewithloader.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {

    val userInfo = UserInfoDto(
        email = "email",
        name = "name",
        surname = "surname",
        phone = "phone",
        bio = "bio"
    )

    suspend fun loadUserInfo(userId: Long): UserInfoDto {
        delay(3000)
        return userInfo
    }
}