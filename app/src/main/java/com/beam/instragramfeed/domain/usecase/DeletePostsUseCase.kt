package com.beam.instragramfeed.domain.usecase

import android.content.Context
import com.beam.instragramfeed.data.repository.PostRepository

class DeletePostsUseCase(context: Context) {
    private val repository = PostRepository(context)

    suspend operator fun invoke() {
        repository.deletePostsFromLocal()
    }
}