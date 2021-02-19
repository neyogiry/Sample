package com.neyogiry.android.sample.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.data.api.ApiClient

class ImageLoaderHelper(private val context: Context) {

    private var glide: GlideRequests = GlideApp.with(context)

    fun load(imageView: ImageView, url: String, progressBar: View? = null) {
        if(url.isEmpty()) glide.clear(imageView)
        progressBar?.visibility = View.VISIBLE
        glide
            .load(formatUrl(context, url))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }

            })
            .apply(requestOptions())
            .placeholder(R.drawable.ic_default_photo)
            .into(imageView)
    }

    companion object {

        const val W92 = "w92"
        const val W154 = "w154"
        const val W185 = "w185"
        const val W342 = "w342"
        const val W500 = "w500"
        const val W780 = "w780"
        const val ORIGINAL = "original"

        private fun formatUrl(context: Context, url: String): String {
            val size = density(context)
            return "${ApiClient.PATH_IMAGE}$size/$url"
        }

        private fun requestOptions(): RequestOptions {
            return RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        private fun density(context: Context): String {
            return when(context.resources.displayMetrics.densityDpi) {
                DisplayMetrics.DENSITY_XXXHIGH, DisplayMetrics.DENSITY_600, DisplayMetrics.DENSITY_560 -> ORIGINAL
                DisplayMetrics.DENSITY_XXHIGH, DisplayMetrics.DENSITY_340, DisplayMetrics.DENSITY_360, DisplayMetrics.DENSITY_400, DisplayMetrics.DENSITY_420, DisplayMetrics.DENSITY_440, DisplayMetrics.DENSITY_450 -> W780
                DisplayMetrics.DENSITY_XHIGH, DisplayMetrics.DENSITY_260, DisplayMetrics.DENSITY_280, DisplayMetrics.DENSITY_300 -> W500
                DisplayMetrics.DENSITY_HIGH, DisplayMetrics.DENSITY_180, DisplayMetrics.DENSITY_200, DisplayMetrics.DENSITY_220 -> W342
                DisplayMetrics.DENSITY_MEDIUM, DisplayMetrics.DENSITY_140 -> W185
                DisplayMetrics.DENSITY_LOW -> W154
                else -> W92

            }
        }

    }

}