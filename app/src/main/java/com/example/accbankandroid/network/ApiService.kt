package com.example.accbankandroid.network

import com.example.accbankandroid.OtpApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

object ApiService {
    private const val BASE_URL = "https://acceinfoapi-cga0hmcdazb5hjbs.eastus2-01.azurewebsites.net/"

    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}

data class OtpVerifyRequest(val otp: String, val token: String)
data class OtpVerifyResponse(val success: Boolean, val message: String)

interface OtpApi {
    @POST("auth/otp-verify")
    suspend fun verifyOtp(
        @Body request: OtpVerifyRequest,
        @Header("Authorization") bearerToken: String
    ): retrofit2.Response<OtpVerifyResponse>
}

object ApiClient {
    val otpApi: OtpApi = Retrofit.Builder()
        .baseUrl("https://acceinfoapi-cga0hmcdazb5hjbs.eastus2-01.azurewebsites.net/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OtpApi::class.java)
}

