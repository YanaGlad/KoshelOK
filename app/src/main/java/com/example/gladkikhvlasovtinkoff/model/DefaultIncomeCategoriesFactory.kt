package com.example.gladkikhvlasovtinkoff.model

import android.app.Activity
import android.content.Context
import com.example.gladkikhvlasovtinkoff.R
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class DefaultIncomeCategoriesFactory(val activity : Activity) : TransactionCategoryDataFactory{
    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = context.getString(R.string.salary),
                iconId = R.drawable.ic_salary,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 76, 166, 0).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.part_time),
                iconId = R.drawable.ic_salary,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 76, 166, 0).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.gift),
                iconId = R.drawable.ic_gift,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 76, 166, 0).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.capitalization),
                iconId = R.drawable.ic_capitalisation,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 76, 166, 0).color
            )
        )
}