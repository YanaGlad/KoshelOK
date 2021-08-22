package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

class CategoryFactory : TransactionCategoryDataFactory {

    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = context.getString(R.string.supermarket),
                iconId = R.drawable.ic_supermarket,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.avia),
                iconId = R.drawable.ic_avia,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.jewelery),
                iconId = R.drawable.ic_jewelry,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.restaurants),
                iconId = R.drawable.ic_restaurants_2,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.pharmancy),
                iconId = R.drawable.ic_pharmacy,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.cafe),
                iconId = R.drawable.ic_restaurants,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.svyaz),
                iconId = R.drawable.ic_svyaz,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.theatre),
                iconId = R.drawable.ic_theatre,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.train),
                iconId = R.drawable.ic_train,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.pets),
                iconId = R.drawable.ic_pets,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.sport),
                iconId = R.drawable.ic_sport,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.inernet),
                iconId = R.drawable.ic_internet,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.public_transport),
                iconId = R.drawable.ic_transport,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.travel),
                iconId = R.drawable.ic_travel,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.charity),
                iconId = R.drawable.ic_charity,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.music),
                iconId = R.drawable.ic_music,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.servicies),
                iconId = R.drawable.ic_services,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.gift),
                iconId = R.drawable.ic_souvenirs,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.mobile),
                iconId = R.drawable.ic_mobile,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.home),
                iconId = R.drawable.ic_home,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.cinema),
                iconId = R.drawable.ic_cinema,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.car),
                iconId = R.drawable.ic_car,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.medicine),
                iconId = R.drawable.ic_medicine,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.education),
                iconId = R.drawable.ic_education,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.tv),
                iconId = R.drawable.ic_tv,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.wallet),
                iconId = R.drawable.ic_wallet,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.clothing),
                iconId = R.drawable.ic_clothing,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.finance),
                iconId = R.drawable.ic_finance,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.entertainment),
                iconId = R.drawable.ic_entertainment,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = context.getString(R.string.other),
                iconId = R.drawable.ic_resource_else,
                id = UNDEFINED_ID,
                description = ""
            )
        )

}