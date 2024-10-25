package com.example.critiqueit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class PredictRequest(val review: String, val model: String = "logistic")
data class PredictResponse(val sentiment: String, val confidence: Double)

interface SentimentApi {
  @POST("/predict")
  fun predictSentiment(@Body request: PredictRequest): Call<PredictResponse>
}