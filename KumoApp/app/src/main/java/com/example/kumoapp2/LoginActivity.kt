package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmax.dialog.SpotsDialog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.UserProfileChangeRequest
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity() {

    //NET CORE API
    lateinit var iMyAPI: IMyAPI
    var compositeDisposable = CompositeDisposable()

    //Firebase AUTH
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        back_to_register_textview.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        login_button.setOnClickListener {
            doLogin()
        }

        /*
        login_button.setOnClickListener {
            val dialog = SpotsDialog.Builder().setContext(this@LoginActivity).build()

            dialog.show()
            doLogin();

            val user = User(username_edittext_register.toString(), password_edittext_login.toString())
            compositeDisposable.addAll(iMyAPI.loginUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                    {s ->
                        Toast.makeText(this@LoginActivity, s, Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    },
                    {
                        t: Throwable? ->
                        Toast.makeText(this@LoginActivity, t!!.message, Toast.LENGTH_SHORT).show()
                        dialog.dismiss()

                    }))

        }*/
    }

    private fun doLogin() {
        if (email_edittext_login.text.toString().isNullOrEmpty()) {
            email_edittext_login.error = "Please enter email"
            email_edittext_login.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_edittext_login.text.toString()).matches()) {
            email_edittext_login.error = "Please enter valid email"
            email_edittext_login.requestFocus()
            return
        }

        if (password_edittext_login.text.toString().isEmpty()) {
            password_edittext_login.error = "Please enter password"
            password_edittext_login.requestFocus()
            return
        }


        auth.signInWithEmailAndPassword(
            email_edittext_login.text.toString(),
            password_edittext_login.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    //If failed
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()

        //Check if user is signed in (non-null) and update UI accordingly
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.d("LoginActivity User:", currentUser.displayName + " : " + currentUser.uid + " : " + currentUser.email );
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT).show()
        }

        /*FOR VERIFICATION MODE:
            if(currentUser != null) {
            if(currentUser.isEmailVerified {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    baseContext, "Please verify your email address",
                    Toast.LENGTH_SHORT
                ).show()
            }*/
    }
}