package com.beam.instragramfeed.di

import com.beam.instragramfeed.data.local.entity.PostObject
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val realmModule = module {
    single {
        val config = RealmConfiguration.create(schema = setOf(PostObject::class))
        Realm.open(config)
    }
}