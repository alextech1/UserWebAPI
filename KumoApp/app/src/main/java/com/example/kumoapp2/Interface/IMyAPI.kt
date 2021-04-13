package com.example.kumoapp2.Interface

import com.example.kumoapp2.Model.*
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Headers

interface IMyAPI {
    //kotlin coroutines
    //@POST("api/User/Register")
    //suspend fun registerUser(@Body user: User): User

    @Headers("Content-Type: application/json")
    @POST("api/User/Register")
    fun registerUser(@Body user: User):Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("api/auth/Login")
    fun loginUser(@Body user: LoginBody):Call<LoginRes>

    @Headers("Content-Type: application/json")
    @POST("api/getProducts")
    fun findProducts(@Body searchStr: String):Call<ProductRes>

    @Headers("Content-Type: application/json")
    @POST("api/getCarts")
    fun findCarts():Call<CartRes>


}