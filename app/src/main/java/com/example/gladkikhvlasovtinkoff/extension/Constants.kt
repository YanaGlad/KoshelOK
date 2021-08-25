package com.example.gladkikhvlasovtinkoff.extension

import com.example.gladkikhvlasovtinkoff.R

const val TRANSACTION_BASE_URL = "http://193.32.218.87:9090/v2/api-docs/"

fun getIconIfByCategoryName(  name : String) : Int=
    when(name){
        "Зарплата"-> R.drawable.ic_salary
        else -> R.drawable.ic_supermarket
    }
