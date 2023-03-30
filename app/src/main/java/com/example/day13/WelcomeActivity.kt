package com.example.day13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.day13.SigninActivity.Companion.KEY1

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val mail = intent.getStringExtra(SigninActivity.KEY1)
        val name = intent.getStringExtra(SigninActivity.KEY2)
        val id = intent.getStringExtra(SigninActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvmail)
        val idText = findViewById<TextView>(R.id.tvid)

        welcomeText.text= "Welcome $name"
        mailText.text= "Mail: $mail"
        idText.text= "User id: $id"

    }
}