package com.example.gladkikhvlasovtinkoff.extension

import android.annotation.SuppressLint
import android.content.Context
import com.example.gladkikhvlasovtinkoff.R
import java.text.SimpleDateFormat
import java.util.*

const val MILLIS_IN_DAY = 86400000

@SuppressLint("SimpleDateFormat")
fun Long.getTimeString() : String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun Long.getDayString(context : Context) : String =
    when((System.currentTimeMillis() - this) / MILLIS_IN_DAY){
        0L -> context.getString(R.string.today)
        1L -> context.getString(R.string.yesterday)
        else -> {
            val date = Date(this)
            val format = SimpleDateFormat("dd MMM")
            format.format(date)
        }
    }


