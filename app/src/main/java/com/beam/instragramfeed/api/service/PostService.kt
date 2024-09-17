package com.beam.instragramfeed.api.service

import com.beam.instragramfeed.data.remote.response.PostResponse
import retrofit2.http.GET

interface PostService {

    @GET("products")
    suspend fun getProducts(): List<PostResponse>
}