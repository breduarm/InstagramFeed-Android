package com.beam.instragramfeed.data.datasource

import com.beam.instragramfeed.data.remote.api.service.PostService
import com.beam.instragramfeed.data.remote.model.PostResponse
import com.beam.instragramfeed.domain.model.Post

interface PostRemoteDataSource {

    suspend fun getPosts(): List<Post>
}

class PostRetrofitDataSource(private val service: PostService) : PostRemoteDataSource {

    override suspend fun getPosts(): List<Post> =
        service.getProducts().map { it.toDomain() }

}

fun PostResponse.toDomain() = Post(
    id = id,
    title = title.orEmpty(),
    description = description.orEmpty(),
    imageUrl = image.orEmpty()
)