package com.beam.instragramfeed.di

import com.beam.instragramfeed.data.datasource.PostLocalDataSource
import com.beam.instragramfeed.data.datasource.PostRealmDataSource
import com.beam.instragramfeed.data.datasource.PostRemoteDataSource
import com.beam.instragramfeed.data.datasource.PostRetrofitDataSource
import com.beam.instragramfeed.data.datasource.PostRoomDataSource
import com.beam.instragramfeed.data.repository.PostRepository
import com.beam.instragramfeed.domain.usecase.DeletePostsUseCase
import com.beam.instragramfeed.domain.usecase.FetchPostsFromRemoteUseCase
import com.beam.instragramfeed.domain.usecase.GetPostsUseCase
import com.beam.instragramfeed.ui.screens.feed.FeedViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::PostRetrofitDataSource) { bind<PostRemoteDataSource>() }
//    singleOf(::PostRoomDataSource) { bind<PostLocalDataSource>() }
    singleOf(::PostRealmDataSource) { bind<PostLocalDataSource>() }
    singleOf(::PostRepository)
    singleOf(::GetPostsUseCase)
    singleOf(::FetchPostsFromRemoteUseCase)
    singleOf(::DeletePostsUseCase)
    viewModelOf(::FeedViewModel)
}