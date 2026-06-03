package com.example.nauraapps.Data.api

import com.example.nauraapps.Data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}