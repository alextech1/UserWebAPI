package com.example.kumoapp2.Interface

import com.example.kumoapp2.Model.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Headers

interface IMyAPI {
    //kotlin coroutines
    //@POST("api/User/Register")
    //suspend fun registerUser(@Body user: User): User

    @Headers("Content-Type:application/json",
        "Authorization:key=API_KEY")

    @POST("api/User/Register")
    fun registerUser(@Body user: User):Call<User>

    @POST("api/auth/Login")
    fun loginUser():Call<LoginRes>
    //fun loginUser(@Body login: LoginRes) //not observable? :Observable<String>

    @POST("api/getProducts")
    fun findAll():Call<List<Product>>

    @POST("api/getCarts")
    fun findCarts():Call<CartRes>


}