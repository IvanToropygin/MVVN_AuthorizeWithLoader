package com.skillbox.mvvn_authorizewithloader

import kotlinx.coroutines.delay
import java.net.ConnectException

class MainRepository {

    private var count = 0

    suspend fun getData(): String {
        delay(3000)
        return if (++count % 2 == 0) {
            throw ConnectException("No internet")
        } else {
             "Done"
        }
    }
}