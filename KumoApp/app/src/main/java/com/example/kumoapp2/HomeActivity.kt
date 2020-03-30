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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import android.view.*
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.iid.FirebaseInstanceId
import okhttp3.*
import java.io.IOException
import com.example.kumoapp2.Common.Common


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private val client = OkHttpClient()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null
    private var mAuthListener : FirebaseAuth.AuthStateListener? = null

    lateinit var txt_user_name:TextView
    lateinit var txt_email_address:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Log.d("__HOME__", "OnCreate")

        //Authorization
        /*FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("newToken", newToken)
            // Send request to set token
            run("http://192.168.198.1:5000/api/setToken/", newToken.toString(), Common.loggedUser!!.Email!!);
        }*/

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
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        //nav listener
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navView.setupWithNavController(navController!!)

        val headerView = navView.getHeaderView(0)
        txt_user_name = headerView.findViewById(R.id.txt_user_name) as TextView
        txt_email_address = headerView.findViewById(R.id.txt_user_email) as TextView
    }

    //Authorization
    /*fun run(url: String, tokens: String, userEmail: String) {
        val contentType = MediaType.parse("application/json; charset=utf-8")
        val body = RequestBody.create(contentType, "{\"Token\":\"$tokens\", \"UserEmail\":\"$userEmail\"}")
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("onResponse", response.message())
            }
        })

    }*/

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
        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().addAuthStateListener(mAuthListener!!);
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

