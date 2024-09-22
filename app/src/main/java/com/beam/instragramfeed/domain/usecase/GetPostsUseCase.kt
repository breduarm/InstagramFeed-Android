package com.beam.instragramfeed.domain.usecase

import android.content.Context
import com.beam.instragramfeed.data.repository.PostRepository

class GetPostsUseCase(context: Context) {
    private val repository = PostRepository(context)

    operator fun invoke() = repository.getPostsFromLocal()
}