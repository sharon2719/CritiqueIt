package com.example.critiqueit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
  private const val BASE_URL =
    "http://13.48.27.185:7000" // Replace with your actual Flask server URL

  val api: SentimentApi by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(SentimentApi::class.java)
  }
}
