package com.example.navdrawerandlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import  kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    //this gets called automatically when sign up is succesful
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {

        val user = firebaseAuth.currentUser
        if (user!=null){
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

    //firebase auth sign up

    fun onSignup(v: View) {

        if(!userEmail.text.toString().isNullOrEmpty() && !userPassword.text.toString().isNullOrEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(userEmail.text.toString(), userPassword.text.toString())
                .addOnCompleteListener { task ->
                    if(!task.isSuccessful) {
                        Toast.makeText(this, "Signup error ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(this, "You succesfully created an account", Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            Toast.makeText(this, "All fields are required",Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        fun newIntent(context: Context?) = Intent(context, SignUpActivity::class.java)
    }
}
