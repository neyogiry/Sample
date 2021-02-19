package com.neyogiry.android.sample.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.neyogiry.android.sample.databinding.ActivityMoviesBinding
import com.neyogiry.android.sample.util.ImageLoaderHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()
        setupEvents()
    }

    private fun initAdapter() {
        val imageLoader = ImageLoaderHelper(this)
        adapter = MoviesAdapter(imageLoader)
        binding.list.adapter = adapter

        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MoviesLoadStateAdapter(adapter),
            footer = MoviesLoadStateAdapter(adapter)
        )

        adapter.addLoadStateListener { loadState ->
            if(adapter.itemCount > 0) {
                binding.loading.isVisible = false
                binding.message.isVisible = false
                binding.retry.isVisible = false
            } else {
                val isLoading = loadState.refresh is LoadState.Loading
                if(isLoading) {
                    binding.loading.isVisible = isLoading
                    binding.message.isVisible = !isLoading
                    binding.retry.isVisible = !isLoading
                } else {
                    binding.loading.isVisible = isLoading
                    val errorState = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        binding.message.isVisible = true
                        binding.retry.isVisible = true
                        binding.message.setText(it.error.message)
                    }
                }

            }

        }

        lifecycleScope.launchWhenCreated {
            moviesViewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setupEvents() {
        binding.retry.setOnClickListener { adapter.retry() }
    }

}