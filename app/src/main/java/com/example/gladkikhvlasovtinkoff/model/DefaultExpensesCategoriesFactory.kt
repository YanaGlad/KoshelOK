package com.example.gladkikhvlasovtinkoff.model

import android.app.Activity
import android.content.Context
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class DefaultExpensesCategoriesFactory(val activity: Activity) : TransactionCategoryDataFactory {

    //TODO - подумать насчет поля description
    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = context.getString(R.string.restaurants),
                iconId = R.drawable.ic_restaurants_2,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 119, 101, 192).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.supermarket),
                iconId = R.drawable.ic_supermarket,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 51, 159, 238).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.sport),
                iconId = R.drawable.ic_sport,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 153, 71, 71).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.public_transport),
                iconId = R.drawable.ic_public_transport,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 238, 51, 186).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.pharmacy),
                iconId = R.drawable.ic_pharmacy,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 22, 220, 113).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.gas_station),
                iconId = R.drawable.ic_gas_station,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 238, 163, 51).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.rent),
                iconId = R.drawable.ic_rent,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 145, 57, 125).color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.travel),
                iconId = R.drawable.ic_travel,
                id = UNDEFINED_ID,
                description = "",
                color = ColorPicker(activity, 238, 219, 51).color
            ),
        )
}