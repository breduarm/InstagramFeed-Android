package com.beam.instragramfeed.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beam.instragramfeed.domain.PostDomain

class FeedViewModel: ViewModel() {

    private val _posts = MutableLiveData<List<PostDomain>>(emptyList())
    val posts: LiveData<List<PostDomain>> = _posts
}
