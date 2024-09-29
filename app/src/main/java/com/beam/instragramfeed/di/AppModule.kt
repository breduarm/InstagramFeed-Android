package com.beam.instragramfeed.di

import com.beam.instragramfeed.data.datasource.PostLocalDataSource
import com.beam.instragramfeed.data.datasource.PostRoomDataSource
import com.beam.instragramfeed.data.repository.PostRepository
import com.beam.instragramfeed.domain.usecase.DeletePostsUseCase
import com.beam.instragramfeed.domain.usecase.FetchPostsFromRemoteUseCase
import com.beam.instragramfeed.domain.usecase.GetPostsUseCase
import com.beam.instragramfeed.ui.screens.feed.FeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PostLocalDataSource> { PostRoomDataSource(get()) }
    single { PostRepository(get()) }
    single { GetPostsUseCase(get()) }
    single { FetchPostsFromRemoteUseCase(get()) }
    single { DeletePostsUseCase(get()) }
    viewModel { FeedViewModel(get(), get(), get()) }
}