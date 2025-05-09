//package com.example.accbankandroid
//
//sealed class NavigationRoutes(val route: String) {
//    object Login : NavigationRoutes("login")
//
//    object LoginOtpScreen : NavigationRoutes("loginOtpScreen/{token}") {
//        fun createRoute(token: String) = "loginOtpScreen/$token"
//    }
//
//    // Update SendMoneyConfirmation to extend NavigationRoutes and implement createRoute correctly
//    object SendMoneyConfirmation : NavigationRoutes("sendMoneyConfirmation/{contactName}/{contactEmail}/{accountName}/{accountNumber}/{transferAmount}/{message}/{securityQuestion}/{securityAnswer}")
//    // Define other routes
//
//
//    object AccountOverview : NavigationRoutes("AccountOverview")
//    object EmailOTPValidationScreen : NavigationRoutes("EmailOTPValidationScreen")
//    object PhoneNumberInputScreen : NavigationRoutes("PhoneNumberInputScreen")
//    object Registration : NavigationRoutes("Registration")
//    object MainScreenWithBottomNav : NavigationRoutes("MainScreenWithBottomNav")
//    object MoveMoney : NavigationRoutes("MoveMoney")
//    object SendMoney : NavigationRoutes("send_money")
//    object AddContact : NavigationRoutes("AddContact")
//    object ConfirmContactScreen : NavigationRoutes("ConfirmContactScreen")
//}
    package com.example.accbankandroid

    import android.net.Uri // Import Uri for encoding

    sealed class NavigationRoutes(val route: String) {
        object Login : NavigationRoutes("login")

        object LoginOtpScreen : NavigationRoutes("loginOtpScreen/{token}") {
            fun createRoute(token: String) = "loginOtpScreen/${Uri.encode(token)}" // Use Uri.encode for safety
        }

        // Update SendMoneyConfirmation to include the buildRoute function
        object SendMoneyConfirmation : NavigationRoutes("sendMoneyConfirmation/{contactName}/{contactEmail}/{accountName}/{accountNumber}/{transferAmount}/{message}/{securityQuestion}/{securityAnswer}") {
            fun buildRoute(
                contactName: String,
                contactEmail: String,
                accountName: String,
                accountNumber: String,
                transferAmount: String,
                message: String?, // Accept nullable String
                securityQuestion: String?, // Accept nullable String
                securityAnswer: String? // Accept nullable String
            ): String {
                // Encode all arguments to handle special characters and spaces safely.
                // Replace nulls with a distinct placeholder string like "null" or an empty string
                // depending on how you want to handle missing optional arguments in the destination composable.
                // Using "null" as a string placeholder is common and matches the NavType.StringType behavior
                // when nullable = true and the argument is not provided.
                val encodedContactName = Uri.encode(contactName)
                val encodedContactEmail = Uri.encode(contactEmail)
                val encodedAccountName = Uri.encode(accountName)
                val encodedAccountNumber = Uri.encode(accountNumber)
                val encodedTransferAmount = Uri.encode(transferAmount)
                val encodedMessage = message?.let { Uri.encode(it) } ?: "null" // Encode if not null, otherwise use "null"
                val encodedSecurityQuestion = securityQuestion?.let { Uri.encode(it) } ?: "null"
                val encodedSecurityAnswer = securityAnswer?.let { Uri.encode(it) } ?: "null"

                return "sendMoneyConfirmation/$encodedContactName/$encodedContactEmail/$encodedAccountName/$encodedAccountNumber/$encodedTransferAmount/$encodedMessage/$encodedSecurityQuestion/$encodedSecurityAnswer"
            }
        }


        ////conform contact
        object ConfirmContactScreen : NavigationRoutes("confirmContact/{name}/{nickname}/{phoneNumber}/{email}/{countryCode}/{mobilePhone}/{sendByEmail}/{sendByPhone}") {

            fun buildRoute(
                name: String,
                nickname: String,
                phoneNumber: String,
                email: String,
                countryCode: String,
                mobilePhone: String,
                sendByEmail: Boolean,
                sendByPhone: Boolean
            ): String {
                val encodedName = Uri.encode(name)
                val encodedNickname = Uri.encode(nickname)
                val encodedPhoneNumber = Uri.encode(phoneNumber)
                val encodedEmail = Uri.encode(email)
                val encodedCountryCode = Uri.encode(countryCode)
                val encodedMobilePhone = Uri.encode(mobilePhone)

                return "confirmContact/$encodedName/$encodedNickname/$encodedPhoneNumber/$encodedEmail/$encodedCountryCode/$encodedMobilePhone/$sendByEmail/$sendByPhone"
            }
        }

        /////conform contact
        // Define other routes
        object AccountOverview : NavigationRoutes("AccountOverview")
        object EmailOTPValidationScreen : NavigationRoutes("EmailOTPValidationScreen")
        object PhoneNumberInputScreen : NavigationRoutes("PhoneNumberInputScreen")
        object Registration : NavigationRoutes("Registration")
        object MainScreenWithBottomNav : NavigationRoutes("MainScreenWithBottomNav")
        object MoveMoney : NavigationRoutes("MoveMoney")
        object SendMoney : NavigationRoutes("SendMoney")

        object AddContact : NavigationRoutes("AddContact")

        object TransferMoney : NavigationRoutes("TransferMoney")

        object TransferMyAccConfirmation : NavigationRoutes("transferMyAccConfirmation/{fromAccountName}/{fromAccountNumber}/{toAccountName}/{toAccountNumber}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}") {

            fun buildRoute(
                fromAccountName: String,
                fromAccountNumber: String,
                toAccountName: String,
                toAccountNumber: String,
                amount: String,
                paymentType: String,
                date: String,
                memo: String?,              // Nullable
                frequency: String?,         // Nullable - only for recurring
                endDate: String?            // Nullable - only for recurring
            ): String {
                // URI-encode all parameters for safe navigation
                val encodedFromAccountName = Uri.encode(fromAccountName)
                val encodedFromAccountNumber = Uri.encode(fromAccountNumber)
                val encodedToAccountName = Uri.encode(toAccountName)
                val encodedToAccountNumber = Uri.encode(toAccountNumber)
                val encodedAmount = Uri.encode(amount)
                val encodedPaymentType = Uri.encode(paymentType)
                val encodedDate = Uri.encode(date)
                val encodedMemo = memo?.let { Uri.encode(it) } ?: "null"
                val encodedFrequency = frequency?.let { Uri.encode(it) } ?: "null"
                val encodedEndDate = endDate?.let { Uri.encode(it) } ?: "null"

                return "transferMyAccConfirmation/$encodedFromAccountName/$encodedFromAccountNumber/$encodedToAccountName/$encodedToAccountNumber/$encodedAmount/$encodedPaymentType/$encodedDate/$encodedMemo/$encodedFrequency/$encodedEndDate"
            }
        }


//another account//
        object TransferToMemberConfirmation : NavigationRoutes(
            "transferToMemberConfirmation/{fromAccountName}/{fromAccountNumber}/{toMemberName}/{toMemberEmail}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}"
        ) {
            fun buildRoute(
                fromAccountName: String,
                fromAccountNumber: String,
                toMemberName: String,
                toMemberEmail: String,
                amount: String,
                paymentType: String,
                date: String,
                memo: String?,              // Nullable
                frequency: String?,         // Nullable
                endDate: String?            // Nullable
            ): String {
                val encodedFromAccountName = Uri.encode(fromAccountName)
                val encodedFromAccountNumber = Uri.encode(fromAccountNumber)
                val encodedToMemberName = Uri.encode(toMemberName)
                val encodedToMemberEmail = Uri.encode(toMemberEmail)
                val encodedAmount = Uri.encode(amount)
                val encodedPaymentType = Uri.encode(paymentType)
                val encodedDate = Uri.encode(date)
                val encodedMemo = memo?.let { Uri.encode(it) } ?: "null"
                val encodedFrequency = frequency?.let { Uri.encode(it) } ?: "null"
                val encodedEndDate = endDate?.let { Uri.encode(it) } ?: "null"

                return "transferToMemberConfirmation/$encodedFromAccountName/$encodedFromAccountNumber/$encodedToMemberName/$encodedToMemberEmail/$encodedAmount/$encodedPaymentType/$encodedDate/$encodedMemo/$encodedFrequency/$encodedEndDate"
            }
        }





        ///success for the my accocunt transfer
        object TransferSuccess : NavigationRoutes(
            "transferSuccess/{fromAccountName}/{fromAccountNumber}/{toAccountName}/{toAccountNumber}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}"
        ) {
            fun buildRoute(
                fromAccountName: String,
                fromAccountNumber: String,
                toAccountName: String,
                toAccountNumber: String,
                amount: String,
                paymentType: String,
                date: String,
                memo: String,
                frequency: String?,
                endDate: String?
            ): String {
                return "transferSuccess/${
                    Uri.encode(fromAccountName)
                }/${
                    Uri.encode(fromAccountNumber)
                }/${
                    Uri.encode(toAccountName)
                }/${
                    Uri.encode(toAccountNumber)
                }/${
                    Uri.encode(amount)
                }/${
                    Uri.encode(paymentType)
                }/${
                    Uri.encode(date)
                }/${
                    Uri.encode(memo)
                }/${
                    frequency?.let { Uri.encode(it) } ?: "null"
                }/${
                    endDate?.let { Uri.encode(it) } ?: "null"
                }"
            }
        }





        ///////////////////////////////////////
        //    object ConfirmContactScreen : NavigationRoutes("ConfirmContactScreen")
    }
