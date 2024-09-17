package com.beam.instragramfeed.data.remote.datasource

import com.beam.instragramfeed.api.retrofit
import com.beam.instragramfeed.api.service.PostService
import com.beam.instragramfeed.data.remote.response.PostResponse
import com.beam.instragramfeed.domain.PostDomain

interface PostDataSource {

    suspend fun getPosts(): List<PostDomain>
}

class PostRemoteDataSource: PostDataSource {
    private val service: PostService = retrofit.create(PostService::class.java)

    override suspend fun getPosts(): List<PostDomain> =
        service.getProducts().map { it.toDomain() }

}

fun PostResponse.toDomain() = PostDomain(
    id = id,
    title = title.orEmpty(),
    description = description.orEmpty(),
    imageUrl = image.orEmpty()
)