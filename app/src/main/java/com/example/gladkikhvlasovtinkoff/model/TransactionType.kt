package com.example.gladkikhvlasovtinkoff.model

import android.content.Context

interface TransactionType {
    fun getName(context : Context) : String
}