package com.beam.instragramfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beam.instragramfeed.data.local.dao.PostDao
import com.beam.instragramfeed.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun postDao(): PostDao
}