package com.example.gladkikhvlasovtinkoff.swipe

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

data class SwipeAction internal constructor(
    @IdRes val actionId: Int,
    val background: Drawable?,
    @DrawableRes val iconId: Int,
    val text: CharSequence?,
    @ColorInt val iconTint: Int = Color.WHITE,
    @ColorInt val textColor: Int = Color.WHITE
) {

    companion object {

        fun withBackgroundColorRes(
            context: Context,
            @IdRes actionId: Int,
            @DrawableRes iconId: Int,
            @ColorInt iconTint: Int = Color.WHITE,
            text: CharSequence?,
            @ColorInt textColor: Int = Color.WHITE,
            @ColorRes backgroundColorRes: Int
        ): SwipeAction {
            val color = ContextCompat.getColor(context, backgroundColorRes)
            return SwipeAction(actionId, ColorDrawable(color), iconId, text, iconTint, textColor)
        }

    }

}
