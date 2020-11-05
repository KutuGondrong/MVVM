package com.kutugondrong.kutugondronggithub.model

import com.squareup.moshi.Json


data class ResponseUsers(@field:Json(name = "items") val users: List<User>)