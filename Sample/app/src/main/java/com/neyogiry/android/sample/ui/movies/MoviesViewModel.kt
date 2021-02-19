package com.neyogiry.android.sample.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.neyogiry.android.sample.data.api.ApiService
import com.neyogiry.android.sample.data.repository.PageKeyedMoviesPagingSource
import com.neyogiry.android.sample.util.SharedPreferencesHelper

class MoviesViewModel(
    private val apiService: ApiService,
    private val apiKey: String,
) : ViewModel() {

    val movies = Pager(
        PagingConfig(pageSize = PAGE_SIZE)
    ) {
        PageKeyedMoviesPagingSource(apiService, apiKey)
    }
        .flow
        .cachedIn(viewModelScope)


    companion object {
        const val PAGE_SIZE = 20
    }

}