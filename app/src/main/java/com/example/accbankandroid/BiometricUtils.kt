package com.example.accbankandroid

import android.os.Build
import androidx.biometric.BiometricManager
inline fun authenticators(aboveVersion9: ()  -> Int, bellowVersion10: () -> Int): Int{
   return if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
        aboveVersion9.invoke()
    }
    else{
        bellowVersion10.invoke()
    }
}
fun BiometricManager.checkExistence(
    onSuccess: (Int) -> Unit,
    onError: (String) ->    Unit,
    openSetting: () ->  Unit
){
    val authenticators = authenticators(aboveVersion9 = {
        BiometricManager.Authenticators.BIOMETRIC_STRONG
    }, bellowVersion10 = {
        BiometricManager.Authenticators.BIOMETRIC_WEAK
    })

    when (canAuthenticate(authenticators)){
        BiometricManager.BIOMETRIC_SUCCESS -> {
            onSuccess.invoke(authenticators)
        }

        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
            onError.invoke("hardware not available")
        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            openSetting.invoke()
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            onError.invoke("hardware not")
        }

        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
            onError.invoke("Security Update require")
        }

        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
            onError.invoke("Biometric Erorr Unsupported")
        }

        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
            onError.invoke("Something Went Wrong")
        }
    }
}