package com.example.dogsgalleryapp.model

import com.google.gson.annotations.SerializedName

data class DogResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    @SerializedName("breeds") val dogBreeds: List<DogBreed>
)