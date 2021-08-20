package com.example.gladkikhvlasovtinkoff.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImageCircle(url: String) =
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .circleCrop()
        .into(this)


fun ImageView.loadImage(url: String) =
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)


fun String.styleText() : String {
    var result = ""
    for (i in this.indices.reversed()) {
        result += this[i]
        if ((i - this.length) % 3 == 0) {
            result+=" "
        }
    }
    return result.reversed() + " ₽"
}

