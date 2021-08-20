package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

class ExpensesType : TransactionType{
    override fun getName(context: Context): String {
        context.getString(R.string.costs_text)
    }

}