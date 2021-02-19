package com.neyogiry.android.sample.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.util.LoggerUtil

class MoviesLoadStateAdapter (
    private val adapter: MoviesAdapter
) : LoadStateAdapter<NetworkStateItemViewHolder>() {

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }

}

class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {

    private val loading: ProgressBar = itemView.findViewById(R.id.loading)
    private val message: TextView = itemView.findViewById(R.id.message)
    private val retry: Button = itemView.findViewById<Button>(R.id.retry)
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        LoggerUtil.i("MLSAdapter", "bindTo() -> " + loadState.toString())
        loading.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        message.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
        message.text = (loadState as? LoadState.Error)?.error?.message
    }
}