package com.example.study_timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resetButton: Button

    private var studyTimeInMillis: Long = 25 * 60 * 1000 // 25 minutes
    private var timeLeftInMillis: Long = studyTimeInMillis
    private var studyTimer: CountDownTimer? = null
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)
        startButton = findViewById(R.id.startButton)
        pauseButton = findViewById(R.id.pauseButton)
        resetButton = findViewById(R.id.resetButton)

        updateTimerText()

        startButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            }
        }

        pauseButton.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            }
        }

        resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        studyTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isTimerRunning = false
                startBreak()
            }
        }.start()

        isTimerRunning = true
    }

    private fun pauseTimer() {
        studyTimer?.cancel()
        isTimerRunning = false
    }

    private fun resetTimer() {
        studyTimer?.cancel()
        timeLeftInMillis = studyTimeInMillis
        updateTimerText()
        isTimerRunning = false
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun startBreak() {
        val intent = Intent(this, BreakActivity::class.java)
        intent.putExtra("break_duration", 5 * 60 * 1000L) // 5 minutes break
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        studyTimer?.cancel()
    }
}