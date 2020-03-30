package com.example.kumoapp2.Interface

import com.example.kumoapp2.Model.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Headers

interface IMyAPI {
    //Authorization
    //@Headers("Content-Type:application/json",
    //    "Authorization:key=AUTHKEYGOESHERE")

    @POST("api/User/Register")
    fun registerUser(@Body user: User):Observable<User>

    @POST("api/auth/Login")
    fun loginUser(@Body user: User) //not observable? :Observable<String>

    @POST("api/getProducts")
    fun findAll():Call<List<Product>>

    @POST("api/getCarts")
    fun findCarts():Call<CartRes>


}