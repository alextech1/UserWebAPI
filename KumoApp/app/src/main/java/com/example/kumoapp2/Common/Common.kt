package com.example.kumoapp2.Common

import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.User

object Common {
    val API_KUMO_ENDPOINT = ""
    val API_KEY = "1234"
    var loggedUser: User?=null
    val USER_INFORMATION: String = "UserInformation"
    val TOKENS: String = "Tokens"

    val fcmService:IMyAPI
        get() = RetrofitClient.getInstance().create(IMyAPI::class.java) //getInstance("https://fcm.googleapis.com")
}