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

fun styleText(text: String) : String {
    var result = ""
    for (i in text.indices.reversed()) {
        result += text[i]
        if ((i - text.length) % 3 == 0) {
            result+=" "
        }
    }
    return result.reversed() + " ₽"
}


fun loadImage(context: Context, url: String?, view: ImageView) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}