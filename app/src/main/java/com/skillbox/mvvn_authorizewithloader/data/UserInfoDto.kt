package com.skillbox.mvvn_authorizewithloader.data

import com.skillbox.mvvn_authorizewithloader.entity.UserInfo

class UserInfoDto(
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val email: String,
    override val bio: String,
) : UserInfo