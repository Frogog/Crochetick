package com.example.crochetick.apiInterface

import com.example.crochetick.dataClass.requestData.AuthRequest
import com.example.crochetick.dataClass.requestData.AuthResponse
import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.dataClass.requestData.LoginRequest
import com.example.crochetick.dataClass.requestData.LoginResponse
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.state.SchemesState
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

    @GET("/api/v1/categories")
    suspend fun getCategories():Response<List<CategoriesResponse>>

    @GET("/api/v1/schemas")
    suspend fun getShemes():Response<List<SchemesResponse>>

}