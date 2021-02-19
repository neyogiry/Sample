package com.neyogiry.android.sample.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.neyogiry.android.sample.databinding.ActivityMoviesBinding
import com.neyogiry.android.sample.util.ImageLoaderHelper
import kotlinx.coroutines.flow.collect
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
    }

    private fun initAdapter() {
        val imageLoader = ImageLoaderHelper(this)
        adapter = MoviesAdapter(imageLoader)
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated {
            moviesViewModel.movies.collect {
                adapter.submitData(it)
            }
        }
    }

}