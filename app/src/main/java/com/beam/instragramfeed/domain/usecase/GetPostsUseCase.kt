package com.beam.instragramfeed.domain.usecase

import com.beam.instragramfeed.data.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {

    operator fun invoke() = repository.getPostsFromLocal()
}