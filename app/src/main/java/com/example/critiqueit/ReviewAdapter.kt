package com.example.critiqueit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private var reviewList: MutableList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_review, parent, false)
    return ReviewViewHolder(view)
  }

  override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
    val review = reviewList[position]
    holder.tvReviewSummary.text = review.summary
    holder.ivMoviePoster.setImageResource(R.drawable.splash) // Placeholder image
  }

  override fun getItemCount(): Int = reviewList.size

  fun updateReviews(newReviews: List<Review>) {
    reviewList.clear()
    reviewList.addAll(newReviews)
    notifyDataSetChanged()
  }

  fun addReview(review: Review) {
    reviewList.add(review)
    notifyItemInserted(reviewList.size - 1) // Notify adapter that a new item has been inserted
  }

  class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvReviewSummary: TextView = view.findViewById(R.id.tv_review_summary) // Assuming this is the ID for the summary TextView
    val ivMoviePoster: ImageView = view.findViewById(R.id.iv_movie_poster) // Assuming this is the ID for the movie poster ImageView
  }
}
