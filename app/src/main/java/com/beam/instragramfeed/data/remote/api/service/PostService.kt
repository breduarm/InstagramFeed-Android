package com.beam.instragramfeed.data.remote.api.service

import com.beam.instragramfeed.data.remote.model.PostResponse
import retrofit2.http.GET

interface PostService {

    @GET("products")
    suspend fun getProducts(): List<PostResponse>
}