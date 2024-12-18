package com.example.critiqueit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NegativeReviewsActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var tvReviewSubmitted: TextView
    private lateinit var tvConfidenceScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_negative_reviews)

        btnBack = findViewById(R.id.btn_back)
        tvReviewSubmitted = findViewById(R.id.tv_review_submitted)
        tvConfidenceScore = findViewById(R.id.tv_confidence_score)

        // Get the review text and confidence score passed from AddReviewActivity
        val reviewText = intent.getStringExtra("REVIEW_TEXT") ?: ""
        val confidenceScore = intent.getDoubleExtra("CONFIDENCE_SCORE", 0.0)

        // Display the review text and confidence score
        tvReviewSubmitted.text = reviewText
        tvConfidenceScore.text = confidenceScore.toString()

        btnBack.setOnClickListener {
            // Start ReviewsActivity and clear this activity from the back stack
            val intent = Intent(this, ReviewsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Clear the activity stack
            startActivity(intent)
            finish() // Close this activity
        }
    }
}
