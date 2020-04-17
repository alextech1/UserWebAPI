package com.example.kumoapp2.Repository

import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.User
import retrofit2.http.Body

class ApiHelper(private val apiService: IMyAPI) {
    suspend fun registerUser(user: User) = apiService.registerUser(user)
}

class UserRepository(private val apiHelper: ApiHelper) {
    //var client: IMyAPI = RetrofitClient.webservice

    suspend fun registerUser(user: User) = apiHelper.registerUser(user)
}