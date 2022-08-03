package com.example.pagingadapter.network

import com.example.pagingadapter.models.CharacterList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int):CharacterList

}