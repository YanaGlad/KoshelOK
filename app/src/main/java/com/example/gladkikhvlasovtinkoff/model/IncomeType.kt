package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

class IncomeType : TransactionType {
    override fun getName(context : Context): String {
        return context.getString(R.string.income_text)
    }
}