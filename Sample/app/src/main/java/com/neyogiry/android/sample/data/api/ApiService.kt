package com.neyogiry.android.sample.data.api

import com.neyogiry.android.sample.data.api.response.MovieDetailResponse
import com.neyogiry.android.sample.data.api.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * REST API access points
 */
interface ApiService {

    @GET("movie/upcoming")
    suspend fun getMovies(@Query("page") page: Int, @Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/{movieId}")
    suspend fun movieDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String) : Response<MovieDetailResponse>

}