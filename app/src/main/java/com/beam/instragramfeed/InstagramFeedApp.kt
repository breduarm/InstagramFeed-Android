package com.beam.instragramfeed

import android.app.Application
import com.beam.instragramfeed.di.appModule
import com.beam.instragramfeed.di.realmModule
import com.beam.instragramfeed.di.retrofitModule
import com.beam.instragramfeed.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class InstagramFeedApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@InstagramFeedApp)
            modules(appModule, roomModule, realmModule, retrofitModule)
        }
    }
}