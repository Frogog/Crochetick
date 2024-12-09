package com.example.crochetick.dataClass.requestData

data class AuthRequest(
    val username:String,
    val password:String,
    val email:String,
    val avatar_color:String
)
data class AuthResponse(
    val id:Int,
    val username:String,
    val email:String,
    val avatar_color:String
)
data class LoginRequest(
    val username: String,
    val password: String
)
data class LoginResponse(
    val access_token:String,
    val token_type:String
)