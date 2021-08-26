package com.example.gladkikhvlasovtinkoff.extension

import com.example.gladkikhvlasovtinkoff.R

const val TRANSACTION_BASE_URL = "http://34.116.183.133:9090/v2/api-docs/"

fun getIconIfByCategoryName(name: String): Int =
    when (name) {
        "Зарплата" -> R.drawable.ic_salary
        else -> R.drawable.ic_supermarket
    }
