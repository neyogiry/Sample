package com.neyogiry.android.sample.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val imagePath: String,
    val releaseDate: String,
    val voteAverage: Double,
)
