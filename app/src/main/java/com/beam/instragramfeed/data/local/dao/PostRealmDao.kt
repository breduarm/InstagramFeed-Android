package com.beam.instragramfeed.data.local.dao

import com.beam.instragramfeed.data.local.entity.PostObject
import io.realm.kotlin.Realm

interface PostRealmDao {

    suspend fun getAll(): List<PostObject>

    suspend fun insertAll(posts: List<PostObject>)

    suspend fun deleteAll()
}

class PostRealmDaoImpl(private val realm: Realm) : PostRealmDao {

    override suspend fun getAll(): List<PostObject> =
        realm.query(PostObject::class).find()

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