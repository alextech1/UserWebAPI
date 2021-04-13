package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.CartRes
import com.example.kumoapp2.Model.ProductRes
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.show_products.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ShowProducts : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private val TAG = "ShowProducts"
    lateinit var myApi: IMyAPI
    private var searchStr: String = ""
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("__ShowProducts__", "onCreate called")
        setContentView(R.layout.show_products)
        Log.d("__ShowProducts__", "setContentView loaded")

        myApi = RetrofitClient.getInstance().create(IMyAPI::class.java)

        find_products.setOnClickListener {
            findProducts(searchStr)
        }
    }

    private fun findProducts(inputStr: String) {
        val paramObject = JSONObject()
        paramObject.put("searchStr", inputStr)
        val instance = SearchList.Factory

        val call = myApi.findProducts(paramObject.toString())
        call.enqueue(object : Callback<ProductRes> {
            override fun onResponse(call: Call<ProductRes>, response: Response<ProductRes>) {
                Log.e(TAG, "message onResponse: " + response.body()!!.message.toString())
                Log.e(TAG, "findProducts onResponse: " + response.body()!!.productList)
                instance.onProductsFound(response.body()!!.productList)
                startActivity(Intent(this@ShowProducts, SearchList::class.java))
            }

            override fun onFailure(call: Call<ProductRes>, t: Throwable) {
                Log.e(TAG, "findProducts onFailure: " + t.localizedMessage)
            }
        })
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

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@ShowProducts, LoginActivity::class.java))
                finish()
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@ShowProducts, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@ShowProducts, Profile::class.java))
            }

            R.id.nav_search -> {
                Log.d("Testing navSearch ", "search")

                startActivity(Intent(this@ShowProducts, SearchList::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@ShowProducts, UpdateInfoActivity::class.java))
            }


        }

        return true
    }


}

