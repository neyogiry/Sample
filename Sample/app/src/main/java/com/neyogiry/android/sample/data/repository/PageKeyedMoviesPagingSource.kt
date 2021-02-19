package com.neyogiry.android.sample.data.repository

import androidx.paging.PagingSource
import com.neyogiry.android.sample.data.api.ApiService
import com.neyogiry.android.sample.domain.model.Movie
import com.neyogiry.android.sample.util.LoggerUtil
import retrofit2.HttpException
import java.io.IOException

class PageKeyedMoviesPagingSource(private val apiService: ApiService, private val apiKey: String) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = apiService.getMovies(nextPage, apiKey)
            val movies = ArrayList<Movie>()

            if(response.isSuccessful) {
                response.body()?.let {
                    it?.movies?.forEach {
                        movies.add(
                            Movie(
                                it.id ?: 0,
                                it.title ?: "",
                                it.overview ?: "",
                                it.imagePath ?: "",
                                it.releaseDate ?: "",
                                it.voteAverage ?: 0.0
                            )
                        )
                    }
                }

                return LoadResult.Page(
                    data = movies,
                    prevKey = null,
                    nextKey = if(movies.isNotEmpty()) nextPage+1 else null
                )

            } else {
                return LoadResult.Error(Throwable("An error has occurred"))
            }

        } catch (e: IOException) {
            // IOException for network failures.
            LoggerUtil.e(TAG, "${e.message}")
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            LoggerUtil.e(TAG, "${e.message}")
            return LoadResult.Error(e)
        }


    }

    companion object {
        private const val TAG = "PageKeyedMoviesPagingSource"
    }

}