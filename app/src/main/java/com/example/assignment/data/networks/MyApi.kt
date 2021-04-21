package com.example.assignment.data.networks

import com.example.assignment.data.models.ArtistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// interfaces to perform an api call and return a response
interface MyApi{

    @GET("search")
    suspend fun getArtistDetail(@Query("term") name: String):Response<ArtistResponse>
}