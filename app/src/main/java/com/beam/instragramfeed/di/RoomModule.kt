package com.beam.instragramfeed.di

import androidx.room.Room
import com.beam.instragramfeed.data.local.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDataBase::class.java,
            name = "instagram-feed-db",
        ).build()
    }
    single { get<AppDataBase>().postDao() }
}