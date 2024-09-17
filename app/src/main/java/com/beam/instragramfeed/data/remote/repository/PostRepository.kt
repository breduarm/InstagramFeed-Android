package com.beam.instragramfeed.data.remote.repository

import com.beam.instragramfeed.data.remote.datasource.PostDataSource
import com.beam.instragramfeed.data.remote.datasource.PostRemoteDataSource
import com.beam.instragramfeed.domain.PostDomain

class PostRepository {
    private val dataSource: PostDataSource = PostRemoteDataSource()

    suspend fun getPosts(): List<PostDomain> = dataSource.getPosts()
}