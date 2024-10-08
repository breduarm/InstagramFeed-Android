package com.beam.instragramfeed.ui.screens.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.instragramfeed.domain.model.Post
import com.beam.instragramfeed.domain.usecase.DeletePostsUseCase
import com.beam.instragramfeed.domain.usecase.FetchPostsFromRemoteUseCase
import com.beam.instragramfeed.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FeedViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val fetchPostsFromRemoteUseCase: FetchPostsFromRemoteUseCase,
    private val deletePostsUseCase: DeletePostsUseCase,
) : KoinComponent, ViewModel() {

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    fun onUiReady() {
        viewModelScope.launch {
            launch {
                getPostsUseCase().collect { collectedPosts ->
                    _posts.value = collectedPosts
                }
            }
            launch(Dispatchers.IO) {
                fetchPostsFromRemoteUseCase()
            }
        }
    }

    fun addToFavorites(post: Post) {
        _posts.value = posts.value?.map { item ->
            if (item.id == post.id) {
                item.copy(isMarkAsFavorite = !post.isMarkAsFavorite)
            } else {
                item
            }
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch {
            deletePostsUseCase()
        }
    }
}