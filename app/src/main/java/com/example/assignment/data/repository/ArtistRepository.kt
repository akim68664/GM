package com.example.assignment.data.repository

import com.example.assignment.data.models.ArtistResponse
import com.example.assignment.data.networks.MyApi
import com.example.assignment.data.networks.SafeApiRequest
import javax.inject.Inject


open class ArtistRepository @Inject constructor (var myApi: MyApi) :SafeApiRequest() {

    suspend fun getData(name:String): ArtistResponse {
        return apiRequest { myApi.getArtistDetail(name) }
    }

}