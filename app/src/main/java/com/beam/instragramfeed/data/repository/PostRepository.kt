package com.beam.instragramfeed.data.repository

import android.content.Context
import com.beam.instragramfeed.data.datasource.PostLocalDataSource
import com.beam.instragramfeed.data.datasource.PostRemoteDataSource
import com.beam.instragramfeed.data.datasource.PostRetrofitDataSource
import com.beam.instragramfeed.data.datasource.PostRoomDataSource
import com.beam.instragramfeed.domain.model.Post

class PostRepository(context: Context) {
    private val remoteDataSource: PostRemoteDataSource = PostRetrofitDataSource()
    private val localDataSource: PostLocalDataSource = PostRoomDataSource(context)

    suspend fun fetchPostsFromRemote(): List<Post> {
        val remotePosts: List<Post> = remoteDataSource.getPosts()
        savePostsToLocal(remotePosts)
        return remotePosts
    }

    suspend fun getPostsFromLocal(): List<Post> = localDataSource.getAllPosts()

    private suspend fun savePostsToLocal(posts: List<Post>) {
        localDataSource.insertPosts(posts)
    }
}