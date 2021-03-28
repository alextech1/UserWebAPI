package com.example.kumoapp2.Utils

import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.User
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Common {
    fun convertTimeStampToDate(time: Long): Date {
        return Date(Timestamp(time).time)
    }

    fun getDateFormatted(date: Date): String? {
        return SimpleDateFormat("dd-MM-yyyy HH:mm").format(date).toString()
    }

    val USER_INFORMATION: String = "UserInformation"
    val TOKENS: String = "Tokens"
    var loggedUser: User?=null
    val USER_UID_SAVE_KEY: String = "SAVE_KEY"

    //val fcmService:IMyAPI
    //    get() = RetrofitClient.getClient("https://fcm.googleapis.com").create(IMyAPI::class.java)
}