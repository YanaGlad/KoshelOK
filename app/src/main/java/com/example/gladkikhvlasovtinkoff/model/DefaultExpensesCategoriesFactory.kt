package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

class DefaultExpensesCategoriesFactory : TransactionCategoryDataFactory {

    //TODO - подумать насчет поля description
    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = context.getString(R.string.restaurants),
                iconId = R.drawable.ic_restaurants_2,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.restraunts_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.supermarket),
                iconId = R.drawable.ic_supermarket,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.supermarket_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.sport),
                iconId = R.drawable.ic_sport,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.sport_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.public_transport),
                iconId = R.drawable.ic_public_transport,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.transport_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.pharmacy),
                iconId = R.drawable.ic_pharmacy,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.pharmacy_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.gas_station),
                iconId = R.drawable.ic_gas_station,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.gas_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.rent),
                iconId = R.drawable.ic_rent,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.rent_dot_color
            ),
            TransactionCategoryData(
                name = context.getString(R.string.travel),
                iconId = R.drawable.ic_travel,
                id = UNDEFINED_ID,
                description = "",
                color = R.color.travel_dot_color
            ),
        )
}