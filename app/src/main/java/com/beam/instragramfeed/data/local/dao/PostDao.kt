package com.beam.instragramfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beam.instragramfeed.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    suspend fun getAll(): List<PostEntity>

    @Insert
    suspend fun saveAll(posts: List<PostEntity>)
}