package com.kutugondrong.kutugondronggithub.network

import com.kutugondrong.kutugondronggithub.model.ResponseUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("q") search : String): Response<ResponseUsers>

}