package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.User
import com.example.kumoapp2.Repository.ApiHelper
import com.example.kumoapp2.Utils.Status
import com.example.kumoapp2.ViewModel.SignUpActivityViewModel
import com.example.kumoapp2.ui.base.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    lateinit var myApi: IMyAPI
    private lateinit var viewModel: SignUpActivityViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        myApi = RetrofitClient.getInstance().create(IMyAPI::class.java)

        register_button_register.setOnClickListener {
            viewModel = ViewModelProviders.of(this,
                ViewModelFactory(ApiHelper(RetrofitClient.webservice))
            ).get(SignUpActivityViewModel::class.java)
            signUpUser()

        }
    }

    private fun signUpUser() {
        if (first_name_et.text.toString().isNullOrEmpty()) {
            first_name_et.error = "Please enter first name"
            first_name_et.requestFocus()
            return
        }
        if (last_name_et.text.toString().isNullOrEmpty()) {
            last_name_et.error = "Please enter last name"
            last_name_et.requestFocus()
        }
        if (username_edittext_register.toString().isNullOrEmpty()) {
            username_edittext_register.error = "Please enter username"
            username_edittext_register.requestFocus()
        }
        if (address_et.toString().isNullOrEmpty()) {
            address_et.error = "Please enter email"
        }

        if (email_edittext_register.text.toString().isNullOrEmpty()) {
            email_edittext_register.error = "Please enter email"
            email_edittext_register.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_edittext_register.text.toString()).matches()) {
            email_edittext_register.error = "Please enter valid email"
            email_edittext_register.requestFocus()
            return
        }

        if (password_edittext_register.text.toString().isEmpty()) {
            password_edittext_register.error = "Please enter password"
            password_edittext_register.requestFocus()
            return
        }

        val role = 1

        var newUser = User(
            first_name_et.text.toString(), last_name_et.text.toString(),
            username_edittext_register.text.toString(), password_edittext_register.text.toString(),
            email_edittext_register.text.toString(), address_et.text.toString(), role
        )

        registerNewUser(newUser)

        //For firebase signup: email and password ONLY
        /*auth.createUserWithEmailAndPassword(email_edittext_register.text.toString(), password_edittext_register.text.toString())
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
                }*/

    }

    //Kotlin coroutines sign up
    private fun registerNewUser(user: User)
    {
        viewModel.registerUser(user).observe(this@SignUpActivity, Observer {
            it?.let {
                    resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.e("SignUp", "signUp onResponse: SUCCESS")
                        Toast.makeText(this@SignUpActivity, "Registration success", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    Status.ERROR -> {
                        Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.e("SignUp", "LOADING")
                    }
                }
            }
        })
    }

    //Regular retrofit signup
    /*private fun registerNewUser(user: User)
    {
        val call = myApi.registerUser(user!!)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("SignUp", "signUp onResponse: " + response.body()!!.toString())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("SignUp", "signUp onFailure: " + t.localizedMessage)
            }
        })
    }*/


}