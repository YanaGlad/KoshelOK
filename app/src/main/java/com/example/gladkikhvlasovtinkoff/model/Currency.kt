package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val code: String,
    val name: String
) : Parcelable {
    val isSetup: Boolean
        get() = code != UNDEFINED_STR && name != UNDEFINED_STR
}