package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Model.Category
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mAuthListener : FirebaseAuth.AuthStateListener? = null
    private val client = OkHttpClient()

    private var drawerLayout: DrawerLayout? = null
    //private lateinit var navView: NavigationView
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null
    var mActionBar: ActionBar? = null

    lateinit var myApi: IMyAPI
    lateinit var txt_user_name:TextView
    lateinit var txt_email_address:TextView

    var user = FirebaseAuth.getInstance().currentUser
    var email = user!!.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("__MainActivity__", "OnCreate")


        initView()

        //Authorization
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("newToken", newToken)
            // Send request to set token
            run("http://10.0.2.2:5000/api/setToken/", newToken.toString(), email!!)
        }


        //myApi = RetrofitClient.getInstance().create(IMyAPI::class.java)

        Log.d("__MAIN__", "OnCreate")
    }

    private fun initView() {
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.addItemDecoration(GridItemDecoration(10, 2))

        val categoryListAdapter = CategoryListGridRecyclerAdapter()
        recycler_view.adapter = categoryListAdapter
        categoryListAdapter.setProductList(generateDummyData())
        
        categoryListAdapter.setOnItemClickListener { category, pos ->
            lateinit var catClick: Intent
            if (category.id == 1)
            {
                catClick = Intent(this, ShowProducts::class.java)
            }
            startActivity(catClick)
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile, R.id.nav_settings,
                R.id.nav_search,
                R.id.nav_slideshow, R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )

        navController = findNavController(R.id.nav_host_fragment)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()

        //(navController!!, appBarConfiguration)
        //navView.setupWithNavController(navController!!)
    }

    //Authorization
    fun run(url: String, tokens: String, userEmail: String) {
        val contentType = MediaType.parse("application/json; charset=utf-8")
        val body = RequestBody.create(
            contentType,
            "{\"Token\":\"$tokens\", \"UserEmail\":\"$userEmail\"}"
        )
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("onResponse", response.message())
            }
        })

    }

    private fun generateDummyData(): List<Category> {
        val listOfCategory = mutableListOf<Category>()

        var categoryModel = Category(1, "Products", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(2, "Food", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(3, "Technology", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(4, "News", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(5, "Economy", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(6, "Sports", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        return listOfCategory
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@MainActivity, Profile::class.java))
            }

            R.id.nav_search -> {
                Log.d("Testing navSearch ", "search")

                startActivity(Intent(this@MainActivity, SearchList::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@MainActivity, UpdateInfoActivity::class.java))
            }
        }

        return true
    }

}
