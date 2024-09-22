package com.beam.instragramfeed.domain.model

data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isMarkAsFavorite: Boolean = false,
)
