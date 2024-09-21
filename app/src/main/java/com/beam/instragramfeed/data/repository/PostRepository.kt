package com.beam.instragramfeed.data.repository

import com.beam.instragramfeed.data.datasource.PostDataSource
import com.beam.instragramfeed.data.datasource.PostRemoteDataSource
import com.beam.instragramfeed.domain.model.PostDomain

class PostRepository {
    private val dataSource: PostDataSource = PostRemoteDataSource()

    suspend fun getPosts(): List<PostDomain> = dataSource.getPosts()
}