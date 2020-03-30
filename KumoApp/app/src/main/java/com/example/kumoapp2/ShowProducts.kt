package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.Cart
import com.example.kumoapp2.Model.CartRes
import com.example.kumoapp2.Model.Product
import com.example.kumoapp2.Model.ProductRes
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowProducts : AppCompatActivity()
{
    private val TAG = "ShowProducts"
    lateinit var myApi: IMyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_products)

        myApi = RetrofitClient.getInstance().create(IMyAPI::class.java)
    }

    fun getCarts(view: View) {
        val call = myApi.findCarts()
        call.enqueue(object : Callback<CartRes> {
            override fun onResponse(call: Call<CartRes>, response: Response<CartRes>) {
                Log.e(TAG, "getCarts onResponse: " + response.body()!!.message.toString())
                Log.e(TAG, "getCarts onResponse: " + response.body()!!.cartList.asList())
            }

            override fun onFailure(call: Call<CartRes>, t: Throwable) {
                Log.e(TAG, "getCarts onFailure: " + t.localizedMessage)
            }
        })
    }

    fun getProdUsingRouteParam(view: View) {

    }

    fun getProdsUsingQuery(view: View) {

    }

    fun postProduct(view: View) {

    }


}