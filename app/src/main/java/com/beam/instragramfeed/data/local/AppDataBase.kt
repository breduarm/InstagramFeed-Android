package com.beam.instragramfeed.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beam.instragramfeed.data.local.dao.PostDao
import com.beam.instragramfeed.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase = instance ?: synchronized(this) {
            val instance: AppDataBase = Room.databaseBuilder(
                context = context.applicationContext,
                klass = AppDataBase::class.java,
                name = "instagram-feed-db",
            ).build()
            this.instance = instance
            instance
        }
    }
}