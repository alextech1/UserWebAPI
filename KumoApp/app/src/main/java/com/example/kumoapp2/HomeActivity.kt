package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import android.view.*
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null
    private lateinit var navView: NavigationView
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null
    private var mAuthListener : FirebaseAuth.AuthStateListener? = null

    lateinit var txt_user_name:TextView
    lateinit var txt_email_address:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("__HOME__", "OnCreate")
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val headerView = navView.getHeaderView(0)
        txt_user_name = headerView.findViewById(R.id.txt_user_name) as TextView
        txt_email_address = headerView.findViewById(R.id.txt_user_email) as TextView

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
                Log.d("Welcome: ", "user")
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile, R.id.nav_slideshow,
                R.id.nav_search, R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )

        navController = findNavController(R.id.nav_host_fragment)

        //nav listener
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()


        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        Log.d("onNavItemSelected", "onNavItemSelected")
        item.isChecked = true
        drawerLayout!!.closeDrawers()

        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().addAuthStateListener(mAuthListener!!)
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            }

            R.id.nav_home -> {
                Log.d("Testing home store ", "kumo store")

                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            }

            R.id.nav_profile -> {
                Log.d("Testing navProfile ", "profile")

                startActivity(Intent(this@HomeActivity, Profile::class.java))
            }

            R.id.nav_search -> {
                Log.d("Testing navSearch ", "search")

                startActivity(Intent(this@HomeActivity, SearchList::class.java))
            }

            R.id.nav_settings -> {
                Log.d("Testing navUpdateInfo ", "updateInfo")

                startActivity(Intent(this@HomeActivity, UpdateInfoActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

