package com.example.gladkikhvlasovtinkoff.model

import android.app.Activity
import android.content.Context
import com.example.gladkikhvlasovtinkoff.R
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class DefaultIncomeCategoriesFactory() : TransactionCategoryDataFactory{
    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = context.getString(R.string.salary),
                iconId = R.drawable.ic_salary,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorGreen = 166,
                colorBlue = 0,
                colorRed = 76
            ),
            TransactionCategoryData(
                name = context.getString(R.string.part_time),
                iconId = R.drawable.ic_salary,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorGreen = 166,
                colorBlue = 0,
                colorRed = 76
            ),
            TransactionCategoryData(
                name = context.getString(R.string.gift),
                iconId = R.drawable.ic_gift,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorGreen = 166,
                colorBlue = 0,
                colorRed = 76
            ),
            TransactionCategoryData(
                name = context.getString(R.string.capitalization),
                iconId = R.drawable.ic_capitalisation,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorGreen = 166,
                colorBlue = 0,
                colorRed = 76
            )
        )
}