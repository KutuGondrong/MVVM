package com.kutugondrong.kutugondronggithub.network

import com.kutugondrong.kutugondronggithub.model.ResponseUsers
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(search: String): Response<ResponseUsers> = apiService.getUsers(search)

}
