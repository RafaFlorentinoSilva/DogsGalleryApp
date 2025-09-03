package com.example.dogsgalleryapp.api

import com.example.dogsgalleryapp.model.DogResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface DogApiService {
    @GET("images/search?limit=10")
    suspend fun getRandomDogs(@Header("x-api-key") apiKey: String): List<DogResponse>
}