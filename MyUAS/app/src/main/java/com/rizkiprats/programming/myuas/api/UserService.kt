package com.rizkiprats.programming.myuas.api

import com.example.eketan.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("hasilapi1")
    fun userLogin(
            @Field("username") username:String,
            @Field("password") password:String
    ): Call<LoginResponse>
}