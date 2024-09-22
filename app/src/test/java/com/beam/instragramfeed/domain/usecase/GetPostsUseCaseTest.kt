package com.beam.instragramfeed.domain.usecase

import com.beam.instragramfeed.data.repository.PostRepository
import com.beam.instragramfeed.domain.model.Post
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostsUseCaseTest {

    @Test
    fun `When invoke is called, then return posts from repository`() {
        val expectedPosts: List<Post> = listOf(
            Post(
                id = 0,
                title = "Title 0",
                description = "Description 0",
                imageUrl = "Image url 0"
            )
        )
        val repository: PostRepository = mockk {
            every { getPostsFromLocal() } returns flowOf(expectedPosts)
        }
        val useCase = GetPostsUseCase(repository)

        val actualPosts: List<Post> = runBlocking {
            useCase().first()
        }

        assertEquals(expectedPosts, actualPosts)
    }
}