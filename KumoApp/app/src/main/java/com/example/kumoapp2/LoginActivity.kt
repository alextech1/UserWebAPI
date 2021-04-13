package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.LoginBody
import com.example.kumoapp2.Model.LoginRes
import com.example.kumoapp2.Utils.Common
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    }

    private fun doLogin() {
        if (username_edittext_login.text.toString().isNullOrEmpty()) {
            username_edittext_login.error = "Please enter username"
            username_edittext_login.requestFocus()
            return
        }

        /*if (!Patterns.EMAIL_ADDRESS.matcher(email_edittext_login.text.toString()).matches()) {
            email_edittext_login.error = "Please enter valid email"
            email_edittext_login.requestFocus()
            return
        }*/

        if (password_edittext_login.text.toString().isEmpty()) {
            password_edittext_login.error = "Please enter password"
            password_edittext_login.requestFocus()
            return
        }

        loginUser(username_edittext_login.text.toString(), password_edittext_login.text.toString())


        //sign in firebase
        /*if (isLoggedIn){
            auth.signInWithEmailAndPassword(
                email,
                password
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
        }*/

    }

    //sign in firebase
    public override fun onStart() {
        super.onStart()

        //Check if user is signed in (non-null) and update UI accordingly
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun loginUser(username: String, password: String){
        val retIn = RetrofitClient.getInstance().create(IMyAPI::class.java)
        Log.d("user___", username)
        val signInInfo = LoginBody(username, password)
        retIn.loginUser(signInInfo).enqueue(object : Callback<LoginRes> {
            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                if (response.code() == 200) {
                    var email = ""
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                    Log.d("response body:", response.toString())
                    email = response.body()!!.email.toString()

                    auth.signInWithEmailAndPassword(
                        email,
                        password
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            //If failed
                            updateUI(null)
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    //sign in firebase
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.d(
                "LoginActivity User:",
                currentUser.displayName + " : " + currentUser.uid + " : " + currentUser.email
            )
            //Common.loggedUser = User(currentUser.uid!!, currentUser.email!!)
            updateToken(currentUser)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT).show()
        }
    }

    //sign in firebase
    private fun updateToken(firebaseUser: FirebaseUser)
    {
        val tokens = FirebaseDatabase.getInstance().getReference(Common.TOKENS)

        //Get token
        FirebaseInstanceId.getInstance().instanceId
            .addOnSuccessListener { instanceIdResult ->
                tokens.child(firebaseUser.uid)
                    .setValue(instanceIdResult.token)
            }.addOnFailureListener { e ->
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}

