package com.example.study_timer

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class BreakActivity : AppCompatActivity() {

    private lateinit var breakTimerText: TextView
    private var breakTimer: CountDownTimer? = null
    private var breakTimeLeftInMillis: Long = 5 * 60 * 1000 // Default 5 minutes
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break)

        breakTimerText = findViewById(R.id.breakTimerText)

        // Get break duration from Intent, if available
        breakTimeLeftInMillis = intent.getLongExtra("break_duration", 5 * 60 * 1000)

        updateBreakTimerText()
        startBreakTimer()
    }

    private fun startBreakTimer() {
        breakTimer = object : CountDownTimer(breakTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                breakTimeLeftInMillis = millisUntilFinished
                updateBreakTimerText()
            }

            override fun onFinish() {
                playNotificationSound()

                // After break, go back to MainActivity
                val intent = Intent(this@BreakActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    private fun updateBreakTimerText() {
        val minutes = (breakTimeLeftInMillis / 1000) / 60
        val seconds = (breakTimeLeftInMillis / 1000) % 60
        breakTimerText.text = String.format("%02d:%02d", minutes, seconds)

        // Change text color if less than 1 minute left
        if (breakTimeLeftInMillis <= 60 * 1000) {
            breakTimerText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        } else {
            breakTimerText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }
    }

    private fun playNotificationSound() {
        mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        breakTimer?.cancel()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
