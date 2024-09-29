package com.beam.instragramfeed.domain.usecase

import com.beam.instragramfeed.data.repository.PostRepository

class DeletePostsUseCase(private val repository: PostRepository) {

    suspend operator fun invoke() {
        repository.deletePostsFromLocal()
    }
}