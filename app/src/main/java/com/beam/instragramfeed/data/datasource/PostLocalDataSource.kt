package com.beam.instragramfeed.data.datasource

import com.beam.instragramfeed.data.local.dao.PostDao
import com.beam.instragramfeed.data.local.entity.PostEntity
import com.beam.instragramfeed.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PostLocalDataSource {

    fun getAllPosts(): Flow<List<Post>>

    suspend fun savePosts(posts: List<Post>)

    suspend fun deletePosts()
}

class PostRoomDataSource(private val postDao: PostDao) : PostLocalDataSource {

    override fun getAllPosts(): Flow<List<Post>> =
        postDao.getAll().map { it.map { postEntity -> postEntity.toDomain() } }

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
