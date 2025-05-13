package com.example.study_timer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var studyButton: Button
    private lateinit var plansButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        studyButton = findViewById(R.id.startStudyButton)
        plansButton = findViewById(R.id.addPlansButton)

        studyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        plansButton.setOnClickListener {
            val intent = Intent(this, PlansActivity::class.java)
            startActivity(intent)
        }
    }
}
