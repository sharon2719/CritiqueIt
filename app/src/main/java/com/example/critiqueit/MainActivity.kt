package com.example.critiqueit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Set OnClickListener on the entire root layout
    val rootLayout = findViewById<View>(R.id.root_layout)
    rootLayout.setOnClickListener {
      // Start MainActivityTwo when the user clicks anywhere on the screen
      val intent = Intent(this, MainActivityTwo::class.java)
      startActivity(intent)
    }
  }
}