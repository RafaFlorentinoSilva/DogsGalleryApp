package com.example.dogsgalleryapp.repository

import com.example.dogsgalleryapp.api.DogApiService
import com.example.dogsgalleryapp.model.DogResponse

class DogRepository(private val apiService: DogApiService, private val apiKey: String) {
    suspend fun getRandomDogs(): List<DogResponse> {
        return apiService.getRandomDogs(apiKey)
    }
}