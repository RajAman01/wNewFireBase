package com.example.newfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        mainNewUserBtn.setOnClickListener {
            val intent = Intent(this, NewUserDetail::class.java)
            startActivity(intent)
        }

        mainLoginBtn.setOnClickListener {
            singup()
        }
    }

    fun singup() {
        if (mainEmailET.text.isEmpty()) {
            mainEmailET.error = "It Cant Be Empty"
            mainEmailET.requestFocus()
            return
        }
        if (mainPasswordET.text.isEmpty()) {
            mainPasswordET.error = "Please Enter your PassWord"
            mainPasswordET.requestFocus()
            return
        }
        mAuth?.signInWithEmailAndPassword(
            mainEmailET.text.toString(),
            mainPasswordET.text.toString()
        )?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "SucessFull Logged In", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoggedUserDetail::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Failled You Are Not User", Toast.LENGTH_LONG).show()
            }
        }
    }

//    public override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth?.currentUser
//        updateUI(currentUser)
//    }
//    fun updateUI(currentUser){
//
//
//    }
}