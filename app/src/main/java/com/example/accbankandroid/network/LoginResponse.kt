package com.example.accbankandroid.network

data class LoginResponse(
    val token: String,
    val name: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val status: String,
    val message: String,
    val statusCode: Int
)
