package com.beam.instragramfeed.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.instragramfeed.domain.PostDomain
import com.beam.instragramfeed.usecase.GetPostsUseCase
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {

    private val getPostsUseCase = GetPostsUseCase()

    private val _posts = MutableLiveData<List<PostDomain>>(emptyList())
    val posts: LiveData<List<PostDomain>> = _posts

    fun onUiReady() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase()
        }
    }

    fun addToFavorites(post: PostDomain) {
        _posts.value = posts.value?.map { item ->
            if (item.id == post.id) {
                item.copy(isMarkAsFavorite = !post.isMarkAsFavorite)
            } else {
                item
            }
        }
    }
}
