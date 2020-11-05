package com.kutugondrong.kutugondronggithub.network

import com.kutugondrong.kutugondronggithub.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}