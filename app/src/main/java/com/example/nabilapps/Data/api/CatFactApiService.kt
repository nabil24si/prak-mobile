package com.example.nabilapps.Data.api

import com.example.nabilapps.Data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}