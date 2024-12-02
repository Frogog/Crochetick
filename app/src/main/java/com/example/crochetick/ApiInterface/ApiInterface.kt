package com.example.crochetick.ApiInterface

import com.example.crochetick.DataClasses.RequestData.AuthRequest
import com.example.crochetick.DataClasses.RequestData.AuthResponse
import com.example.crochetick.DataClasses.RequestData.LoginRequest
import com.example.crochetick.DataClasses.RequestData.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/v1/users")
    suspend fun auth(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/v1/users/token")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/api/v1/users/me")
    suspend fun getUser(
        @Header("Authorization") authorization:String
    ): Response<AuthRequest>
}