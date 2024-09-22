package com.beam.instragramfeed.data.datasource

import android.content.Context
import com.beam.instragramfeed.data.local.AppDataBase
import com.beam.instragramfeed.data.local.dao.PostDao
import com.beam.instragramfeed.data.local.entity.PostEntity
import com.beam.instragramfeed.domain.model.Post

interface PostLocalDataSource {

    suspend fun getAllPosts(): List<Post>

    suspend fun savePosts(posts: List<Post>)

    suspend fun deletePosts()
}

class PostRoomDataSource(context: Context) : PostLocalDataSource {
    private val postDao: PostDao = AppDataBase.getDataBase(context).postDao()

    override suspend fun getAllPosts(): List<Post> = postDao.getAll().map { it.toDomain() }

    override suspend fun savePosts(posts: List<Post>) {
        postDao.insertAll(posts.map { it.toEntity() })
    }

    override suspend fun deletePosts() {
        postDao.deleteAll()
    }
}

private fun Post.toEntity() = PostEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
)

private fun PostEntity.toDomain(): Post = Post(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
)
