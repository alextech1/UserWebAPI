package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_update_info.*
import kotlinx.android.synthetic.main.activity_update_info.drawer_layout


class UpdateInfoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var profile: Profile? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("__UpdateInfo__", "onCreate called")
        setContentView(R.layout.activity_update_info)
        Log.d("__UpdateInfo__", "setContentView loaded")
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile, R.id.nav_settings,
                R.id.nav_slideshow, R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )

        navController = findNavController(R.id.nav_host_fragment)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()
        val instance = Profile.Factory

        btn_update.setOnClickListener {
            //profile!!.onStatusUpdateReceived("testing status")
            //on_status_change.text = "testing update"
            instance.onStatusUpdateReceived("testing update")

        }
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        Log.d("onNavItemSelected", "onNavItemSelected")

        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@UpdateInfoActivity, LoginActivity::class.java))
                finish()
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@UpdateInfoActivity, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@UpdateInfoActivity, Profile::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@UpdateInfoActivity, UpdateInfoActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
