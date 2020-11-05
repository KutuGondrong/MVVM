package com.kutugondrong.kutugondronggithub.network

import com.kutugondrong.kutugondronggithub.model.ResponseUsers
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(search: String): Response<ResponseUsers>
}