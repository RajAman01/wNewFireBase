package com.example.newfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_new_user_detail.*

class NewUserDetail : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_detail)
        mAuth = FirebaseAuth.getInstance()

        newPersonCreateBtn.setOnClickListener {
            createId()
        }

    }

    private fun createId() {
        if (newPersonNameET.text.isEmpty()) {
            newPersonNameET.error = "Name Can't Be Empty "
            newPersonNameET.requestFocus()
            return
        }
        if (newPersonAgeET.text.isEmpty()) {
            newPersonAgeET.error = "Age Can't Be Empty"
            newPersonAgeET.requestFocus()
            return
        }
        if (newPersonPassET.text.isEmpty()) {
            newPersonPassET.error = "Please Enter your Password"
            newPersonPassET.requestFocus()
            return
        }
        if (newPersonRePassET.text.isEmpty()) {
            newPersonRePassET.error = "Please Re Enter your Password"
            newPersonRePassET.requestFocus()
            return
        }
        if (newPersonPassET.text.toString() != newPersonRePassET.text.toString()) {
            Toast.makeText(this, "PassWord Not Match", Toast.LENGTH_LONG).show()
            newPersonPassET.text.clear()
            newPersonRePassET.text.clear()
            return

        }
        mAuth?.createUserWithEmailAndPassword(
            newPersonEmailET.text.toString(),
            newPersonPassET.text.toString().trim()
        )
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Account Not Created", Toast.LENGTH_LONG).show()
                }
            }
    }
}