package com.beam.instragramfeed.usecase

import com.beam.instragramfeed.data.remote.repository.PostRepository

class GetPostsUseCase {
    private val repository = PostRepository()

    suspend operator fun invoke() = repository.getPosts()
}