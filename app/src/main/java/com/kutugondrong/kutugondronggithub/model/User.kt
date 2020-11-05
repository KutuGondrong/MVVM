package com.kutugondrong.kutugondronggithub.model

import com.squareup.moshi.Json

data class User(@field:Json(name = "avatar_url") val avatar_url: String,
                @field:Json(name = "login") val userName: String)