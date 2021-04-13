package com.example.kumoapp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.kumoapp2.Model.Product
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_info.*


class SearchList : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object Factory {
        private var productList: List<Product> = emptyList()

        fun onProductsFound(body: List<Product>) {
            productList = body
        }
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null
    var simpleList: ListView? = null
    var flags = intArrayOf(
        R.drawable.kumo_logo,
        R.drawable.kumo_logo,
        R.drawable.kumo_logo,
        R.drawable.kumo_logo,
        R.drawable.kumo_logo,
        R.drawable.kumo_logo
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_list)
        Log.d("__SearchList__", "OnCreate")
        simpleList = findViewById(R.id.simpleListView)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.search_list_drawer)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile, R.id.nav_settings,
                R.id.nav_search,
                R.id.nav_slideshow, R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )

        navController = findNavController(R.id.nav_host_fragment)

        val customAdapter = CustomAdapter(applicationContext, productList, flags)
        simpleList!!.adapter = customAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("onNavItemSelected", "onNavItemSelected")

        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@SearchList, LoginActivity::class.java))
                finish()
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@SearchList, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@SearchList, Profile::class.java))
            }

            R.id.nav_search -> {
                Log.d("Testing navSearch ", "search")

                startActivity(Intent(this@SearchList, SearchList::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@SearchList, UpdateInfoActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}