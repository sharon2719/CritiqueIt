package com.example.critiqueit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityTwo : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_two)

    // Find the button by its ID
    val buttonReviews: Button = findViewById(R.id.get_started_button)

    // Set an OnClickListener on the button
    buttonReviews.setOnClickListener {
      // Create an Intent to navigate to ReviewsActivity
      val intent = Intent(this, ReviewsActivity::class.java)
      startActivity(intent)
    }
  }
}