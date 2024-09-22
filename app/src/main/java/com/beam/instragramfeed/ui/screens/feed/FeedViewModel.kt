package com.beam.instragramfeed.ui.screens.feed

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.beam.instragramfeed.domain.model.Post
import com.beam.instragramfeed.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.launch

class FeedViewModel(context: Context) : ViewModel() {

    private val getPostsUseCase = GetPostsUseCase(context)

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    fun onUiReady() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase()
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
}

class FeedViewModelFactory(
    private val context: Context,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}