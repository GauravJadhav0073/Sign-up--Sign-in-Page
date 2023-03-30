package com.example.day13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SigninActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.day13.SigninActivity.mail"
        const val KEY2 = "com.example.day13.SigninActivity.name"
        const val KEY3 = "com.example.day13.SigninActivity.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val btnsignin = findViewById<Button>(R.id.btnsignin)
        val userid = findViewById<TextInputEditText>(R.id.uid)

        btnsignin.setOnClickListener {

            val userNameString = userid.text.toString()
            if(userNameString.isNotEmpty()){
                readData(userNameString)
            }else{
                Toast.makeText(this, "Please Enter username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userNameString: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(userNameString).get().addOnSuccessListener {

            if(it.exists()){
                val mail = it.child("mail").value
                val name = it.child("name").value
                val id = it.child("uniqueId").value

                val intentWelcome = Intent(this,WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1,mail.toString())
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY3,id.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this, "User does not exist, plz first sign up", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed, Error in DB", Toast.LENGTH_SHORT).show()
        }
    }
}