package com.example.critiqueit

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
  val tvReviewSummary: TextView = itemView.findViewById(R.id.tv_review_summary)

}