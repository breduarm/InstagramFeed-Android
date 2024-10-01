package com.beam.instragramfeed.data.local.dao

import com.beam.instragramfeed.data.local.entity.PostObject
import io.realm.kotlin.Realm
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

interface PostRealmDao {

    fun getAll(): Flow<List<PostObject>>

    suspend fun insertAll(posts: List<PostObject>)

    suspend fun deleteAll()
}

@OptIn(ExperimentalCoroutinesApi::class)
class PostRealmDaoImpl(private val realm: Realm) : PostRealmDao {

    override fun getAll(): Flow<List<PostObject>> =
        realm.query(PostObject::class).asFlow().mapLatest { it.list }

    override suspend fun insertAll(posts: List<PostObject>) {
        realm.writeBlocking {
            posts.forEach { post ->
                copyToRealm(post)
            }
        }
    }

    override suspend fun deleteAll() {
        realm.writeBlocking {
            val posts = query(PostObject::class).find()
            delete(posts)
        }
    }
}