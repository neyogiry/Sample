package com.neyogiry.android.sample.data.api.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("title") val title: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val imagePath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = 0.0,

    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
)
