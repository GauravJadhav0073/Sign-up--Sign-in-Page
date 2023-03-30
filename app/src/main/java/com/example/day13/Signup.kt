package com.example.day13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnsignup = findViewById<Button>(R.id.btnsignup)
        val etname = findViewById<TextInputEditText>(R.id.etname)
        val etmail = findViewById<TextInputEditText>(R.id.etmail)
        val etpassword = findViewById<TextInputEditText>(R.id.etpassword)
        val etid = findViewById<TextInputEditText>(R.id.etid)

        btnsignup.setOnClickListener {

            val name = etname.text.toString()
            val mail = etmail.text.toString()
            val password = etpassword.text.toString()
            val uniqueid = etid.text.toString()

            val user = Users(name, mail, password,uniqueid)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueid).setValue(user).addOnSuccessListener {

                etname.text?.clear()
                etmail.text?.clear()
                etpassword.text?.clear()
                etid.text?.clear()

                Toast.makeText(this, "User Registered Succesfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to Registered", Toast.LENGTH_SHORT).show()
            }

        }
        val signin = findViewById<TextView>(R.id.tvsignin)
        signin.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
    }
}
