package com.example.critiqueit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddReviewActivity : AppCompatActivity() {
  private lateinit var etReview: EditText
  private lateinit var btnSendReview: Button
  private lateinit var btnBack: ImageButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_review) // Ensure this matches your layout file name

    // Initialize the UI components
    etReview = findViewById(R.id.et_review)
    btnSendReview = findViewById(R.id.btn_send_review)
    btnBack = findViewById(R.id.btn_back)

    // Set click listener for the back button
    btnBack.setOnClickListener {
      finish() // Close this activity and return to the previous one
    }

    // Set click listener for the send review button
    btnSendReview.setOnClickListener {
      val reviewText = etReview.text.toString() // Get the text from EditText
      if (reviewText.isNotEmpty()) {
        analyzeReview(reviewText)
      } else {
        etReview.error = "Please enter a review" // Set error on EditText
      }
    }
  }

  private fun analyzeReview(reviewText: String) {
    // Create request object
    val request = PredictRequest(review = reviewText)

    // Make the API call using Retrofit
    RetrofitInstance.api.predictSentiment(request).enqueue(object : Callback<PredictResponse> {
      override fun onResponse(call: Call<PredictResponse>, response: Response<PredictResponse>) {
        if (response.isSuccessful) {
          val sentiment = response.body()?.sentiment ?: "Unknown"
          val confidence = response.body()?.confidence ?: 0.0

          // Navigate to Positive or Negative screen based on the sentiment
          val targetActivity = if (sentiment.equals("positive", ignoreCase = true)) {
            PositiveReviewsActivity::class.java
          } else {
            NegativeReviewsActivity::class.java
          }

          // Start the new activity with the sentiment details
          val intent = Intent(this@AddReviewActivity, targetActivity).apply {
            putExtra("REVIEW_TEXT", reviewText)
            putExtra("SENTIMENT", sentiment)
            putExtra("CONFIDENCE_SCORE", confidence)
          }
          startActivity(intent)
          finish() // Optional: finish current activity
        } else {
          Toast.makeText(this@AddReviewActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
        }
      }

      override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
        Toast.makeText(this@AddReviewActivity, "Failed to connect: ${t.message}", Toast.LENGTH_SHORT).show()
      }
    })
  }

}
