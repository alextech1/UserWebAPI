package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed( {
            startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}