package com.ny.times.nytimesarticles.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.ny.times.nytimesarticles.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                // Start the next activity
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                // Finish the current activity
                finish()
            }
        }.start()
    }
    }
