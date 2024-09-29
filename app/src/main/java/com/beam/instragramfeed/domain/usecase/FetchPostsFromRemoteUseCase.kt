package com.beam.instragramfeed.domain.usecase

import com.beam.instragramfeed.data.repository.PostRepository

class FetchPostsFromRemoteUseCase(private val repository: PostRepository) {

    suspend operator fun invoke() = repository.fetchPostsFromRemote()
}