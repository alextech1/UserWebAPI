package com.example.kumoapp2

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Model.User
import com.example.kumoapp2.Model.UserFirebase
import com.example.kumoapp2.Utils.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId


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
            Log.d("LoginActivity User:", currentUser.displayName + " : " + currentUser.uid + " : " + currentUser.email )
            //Common.loggedUser = User(currentUser.uid!!, currentUser.email!!)
            updateToken(currentUser)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT).show()
        }
    }

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