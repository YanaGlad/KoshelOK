package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Currency (
    var code : String,
    var name : String
    ) : Parcelable