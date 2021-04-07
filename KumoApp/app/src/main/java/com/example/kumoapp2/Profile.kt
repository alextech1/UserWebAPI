package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.drawer_layout
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object Factory {
        private var statusTemp: String? = "No status available"

        fun onStatusUpdateReceived(body: String) {
            statusTemp = body
        }
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d("__PROFILE__", "onCreate")
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile, R.id.nav_slideshow,
                R.id.nav_search, R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )

        navController = findNavController(R.id.nav_host_fragment)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()

        statusTemp?.let { updateStatus(it) }

        //setupActionBarWithNavController(navController!!, appBarConfiguration)
        //navView.setupWithNavController(navController!!)

        Log.d("__onCreate__", "done")
    }


    private fun updateStatus(body: String) {
        on_status_change.text = statusTemp
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        Log.d("onNavItemSelected", "onNavItemSelected")

        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@Profile, LoginActivity::class.java))
                finish()
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@Profile, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@Profile, Profile::class.java))
            }

            R.id.nav_search -> {
                Log.d("Testing navSearch ", "search")

                startActivity(Intent(this@Profile, SearchList::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@Profile, UpdateInfoActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }




}