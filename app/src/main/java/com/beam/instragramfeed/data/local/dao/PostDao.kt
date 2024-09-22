package com.beam.instragramfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beam.instragramfeed.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    suspend fun getAll(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("DELETE FROM PostEntity")
    suspend fun deleteAll()
}