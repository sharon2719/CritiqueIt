package com.example.critiqueit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

  class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash) // Reference to splash_screen.xml

      val rootView = findViewById<View>(android.R.id.content) // Get the root view
      rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundColor))
      // Delay the transition to the MainActivity
      val splashScreenDuration = 2000L // 2000 milliseconds = 2 seconds
      window.decorView.postDelayed({
        // Navigate to MainActivity after the splash screen
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Close the SplashActivity
      }, splashScreenDuration)
    }
}