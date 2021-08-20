package com.example.gladkikhvlasovtinkoff.swipe.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.Px


@Px
internal fun Int.toPx(units: Int = TypedValue.COMPLEX_UNIT_DIP): Int {
    return TypedValue.applyDimension(
        units, this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

internal fun Float.toPx(units: Int = TypedValue.COMPLEX_UNIT_DIP): Float {
    return TypedValue.applyDimension(
        units, this,
        Resources.getSystem().displayMetrics
    )
}
