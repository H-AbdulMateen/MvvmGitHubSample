package com.iislex.mvvmgithubsample.network

import com.iislex.mvvmgithubsample.network.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {

    @GET("users/{user}")
    suspend fun getUseInfo(
        @Path("user") username: String
    ): UserDto

}