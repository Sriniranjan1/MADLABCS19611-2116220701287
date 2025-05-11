package com.example.formvalidation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val etUserName : EditText = findViewById(R.id.etUserName)
        val etPinNumber : EditText = findViewById(R.id.etPinNumber)
        val btLogin : Button = findViewById(R.id.btLogin)
        val btClear : Button = findViewById(R.id.btClear)
        btLogin.setOnClickListener {
            val checkUserName = "[a-zA-Z]+".toRegex()
            val checkPinNo = "[0-9]{4}".toRegex()
            if(checkUserName.matches(etUserName.text.toString()) &&
                checkPinNo.matches(etPinNumber.text.toString())) {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext, "Invalid User Name / Pin No."
                    ,Toast.LENGTH_LONG).show()
            }
        }
        btClear.setOnClickListener {
            etUserName.text.clear()
            etPinNumber.text.clear()
        }
    }
}