package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Currency (
    val id : Long,
    val code : String,
    val name : String
    ) : Parcelable