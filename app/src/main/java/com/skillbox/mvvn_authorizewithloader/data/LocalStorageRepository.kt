package com.skillbox.mvvn_authorizewithloader.data

import com.skillbox.mvvn_authorizewithloader.entity.UserInfo
import kotlinx.coroutines.delay

class LocalStorageRepository {

    suspend fun saveUserInfo(userInfo: UserInfo) {
        delay(1000)
    }
}