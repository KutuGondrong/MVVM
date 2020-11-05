package com.kutugondrong.kutugondronggithub.network.repository

import com.kutugondrong.kutugondronggithub.network.ApiHelper
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers(search: String) =  apiHelper.getUsers(search)

}