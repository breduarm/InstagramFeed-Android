package com.beam.instragramfeed.domain.usecase

import com.beam.instragramfeed.data.repository.PostRepository

class GetPostsUseCase {
    private val repository = PostRepository()

    suspend operator fun invoke() = repository.getPosts()
}