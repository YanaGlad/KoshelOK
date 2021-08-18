package com.example.gladkikhvlasovtinkoff.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener

fun loadImageCircle(context: Context, url: String?, view: ImageView) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .circleCrop()
        .into(view)
}

fun loadImage(
    context: Context,
    url: String?,
    view: ImageView,
    requestListener: RequestListener<Drawable>
) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(requestListener)
        .into(view)
}


fun loadImage(context: Context, url: String?, view: ImageView) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}