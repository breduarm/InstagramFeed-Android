package com.beam.instragramfeed.data.repository

import com.beam.instragramfeed.data.datasource.PostLocalDataSource
import com.beam.instragramfeed.data.datasource.PostRemoteDataSource
import com.beam.instragramfeed.data.datasource.PostRetrofitDataSource
import com.beam.instragramfeed.domain.model.Post
import kotlinx.coroutines.flow.Flow

class PostRepository(private val localDataSource: PostLocalDataSource) {
    private val remoteDataSource: PostRemoteDataSource = PostRetrofitDataSource()

    suspend fun fetchPostsFromRemote(): List<Post> {
        val remotePosts: List<Post> = remoteDataSource.getPosts()
        savePostsToLocal(remotePosts)
        return remotePosts
    }

    fun getPostsFromLocal(): Flow<List<Post>> = localDataSource.getAllPosts()

    private suspend fun savePostsToLocal(posts: List<Post>) {
        localDataSource.savePosts(posts)
    }

    suspend fun deletePostsFromLocal() {
        localDataSource.deletePosts()
    }
}