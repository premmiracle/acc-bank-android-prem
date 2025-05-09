package com.example.accbankandroid.network

data class LoginRequest(
    val username: String,
    val password: String,
    val type: String = "customer",
    val refreshToken: String = ""
)
