package com.example.critiqueit

data class Review(
  val id: Int,
  val summary: String,
  val sentiment: String,
  val confidence: Double
)

