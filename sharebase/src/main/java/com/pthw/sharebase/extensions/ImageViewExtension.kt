package com.pthw.sharebase.extensions

import android.os.Build
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.pthw.sharebase.R

/**
 * Created by P.T.H.W on 02/10/2023.
 */

private const val SCHEME_HTTPS = "https"

fun ImageView.loadFromUrl(imageUrl: String) {
    try {
        val url = imageUrl.toUri().buildUpon()?.scheme(SCHEME_HTTPS)?.build()
        Glide.with(context)
            .load(url)
            .error(R.color.glider_placholder)
            .placeholder(R.color.glider_placholder)
            .centerCrop()
            .sizeMultiplier(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) 1f else 0.6f)
            .into(this)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
    }
}