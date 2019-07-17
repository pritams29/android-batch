package com.example.retrofit.network

import android.telecom.Call
import retrofit2.http.GET
import com.example.retrofit.model.RetroPhoto as RetroPhoto1

interface GetDataService {
    @get:GET("/photos")
    val allPhotos: Call<List<RetroPhoto1>>
}