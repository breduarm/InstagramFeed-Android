package com.beam.instragramfeed.domain

data class PostDomain(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isMarkAsFavorite: Boolean = false,
)
