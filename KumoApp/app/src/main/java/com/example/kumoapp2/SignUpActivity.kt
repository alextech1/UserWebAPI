package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        register_button_register.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        if(email_edittext_register.text.toString().isNullOrEmpty()) {
            email_edittext_register.error = "Please enter email"
            email_edittext_register.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_edittext_register.text.toString()).matches())
        {
            email_edittext_register.error = "Please enter valid email"
            email_edittext_register.requestFocus()
            return
        }

        if(password_edittext_register.text.toString().isEmpty()){
            password_edittext_register.error = "Please enter password"
            password_edittext_register.requestFocus()
            return
        }

        var strUserName = username_edittext_register.text.toString();

        auth.createUserWithEmailAndPassword(email_edittext_register.text.toString(), password_edittext_register.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = FirebaseAuth.getInstance().currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(strUserName).build()
                    user!!.updateProfile(profileUpdates)

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                /* FOR VERIFICATION MODE:
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }*/
            }
    }



}