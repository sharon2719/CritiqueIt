package com.example.critiqueit

import ReviewDatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReviewsActivity : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var reviewAdapter: ReviewAdapter
  private lateinit var ivEmptyState: ImageView
  private lateinit var tvEmptyMessageTitle: TextView
  private lateinit var tvEmptyMessageSubtitle: TextView
  private lateinit var fabAddReview: FloatingActionButton
  private lateinit var databaseHelper: ReviewDatabaseHelper // Reference to the DatabaseHelper

  companion object {
    const val REQUEST_ADD_REVIEW = 1
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_reviews)

    // Initialize views
    recyclerView = findViewById(R.id.recycler_view_reviews)
    ivEmptyState = findViewById(R.id.iv_empty_state)
    tvEmptyMessageTitle = findViewById(R.id.tv_empty_message_title)
    tvEmptyMessageSubtitle = findViewById(R.id.tv_empty_message_subtitle)
    fabAddReview = findViewById(R.id.fab_add_review)

    // Initialize DatabaseHelper
    databaseHelper = ReviewDatabaseHelper(this)

    // Set up RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(this)
    reviewAdapter = ReviewAdapter(mutableListOf())
    recyclerView.adapter = reviewAdapter

    // Load existing reviews from the database
    loadReviews()

    // Set click listener to add review
    fabAddReview.setOnClickListener {
      val intent = Intent(this, AddReviewActivity::class.java)
      startActivityForResult(intent, REQUEST_ADD_REVIEW)
    }
  }

  private fun loadReviews() {
    val reviews = databaseHelper.getAllReviews()
    reviewAdapter.updateReviews(reviews) // Update the adapter with fetched reviews

    // Check if the adapter is empty and update UI accordingly
    if (reviewAdapter.itemCount == 0) {
      showEmptyState()
    } else {
      showReviews()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_ADD_REVIEW && resultCode == RESULT_OK) {
      data?.let {
        val reviewText = it.getStringExtra("REVIEW_TEXT") ?: ""
        val sentiment = it.getStringExtra("SENTIMENT") ?: "Neutral"
        val confidence = it.getDoubleExtra("CONFIDENCE_SCORE", 0.0)

        // Create a new Review object
        val newReview = Review(
          id = 0,
          summary = reviewText,
          sentiment = sentiment,
          confidence = confidence
        )

        // Add the new review to the adapter directly
        reviewAdapter.addReview(newReview)

        // Check if the adapter is empty and update UI accordingly
        if (reviewAdapter.itemCount == 0) {
          showEmptyState()
        } else {
          showReviews()
        }
      }
    }
  }

  private fun showEmptyState() {
    recyclerView.visibility = View.GONE
    ivEmptyState.visibility = View.VISIBLE
    tvEmptyMessageTitle.visibility = View.VISIBLE
    tvEmptyMessageSubtitle.visibility = View.VISIBLE
  }

  private fun showReviews() {
    recyclerView.visibility = View.VISIBLE
    ivEmptyState.visibility = View.GONE
    tvEmptyMessageTitle.visibility = View.GONE
    tvEmptyMessageSubtitle.visibility = View.GONE
  }
}
