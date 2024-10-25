package com.example.critiqueit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private var reviewList: MutableList<Review>) : RecyclerView.Adapter<ReviewViewHolder>() {

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
}
