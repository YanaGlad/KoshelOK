package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.gladkikhvlasovtinkoff.R
import com.pes.androidmaterialcolorpickerdialog.ColorPicker


enum class UserCateroryKeys {
    Supermarket, Avia, Jewelery, Restaurants, Pharmancy, Cafe, Connection, Theatre, Train, Pets, Sport, Inernet, Transport, Medicine,
    Travel, Charity, Music, Service, Gift, Mobile, Home, Cinema, Car, Education, Tv, Wallet, Clothing, Finance, Entertainment, Other
}

class CategoryFactory(activity: AppCompatActivity) : TransactionCategoryDataFactory {
    var colorRed: Int = 89
        set(value) {
            field = value
        }

    var colorGreen: Int =  77
        set(value) {
            field = value
        }

    var colorBlue: Int = 244
        set(value) {
            field = value
        }

    var color: Int = ColorPicker(activity,colorRed, colorGreen, colorBlue).color
        set(value) {
            field = value
        }

    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = UserCateroryKeys.Supermarket.name,
                iconId = R.drawable.ic_supermarket,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Avia.name,
                iconId = R.drawable.ic_avia,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Jewelery.name,
                iconId = R.drawable.ic_jewelry,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Restaurants.name,
                iconId = R.drawable.ic_restaurants_user,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Pharmancy.name,
                iconId = R.drawable.ic_pharmacy,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Cafe.name,
                iconId = R.drawable.ic_restaurants,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Connection.name,
                iconId = R.drawable.ic_svyaz,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Theatre.name,
                iconId = R.drawable.ic_theatre,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Train.name,
                iconId = R.drawable.ic_train,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Pets.name,
                iconId = R.drawable.ic_pets,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Sport.name,
                iconId = R.drawable.ic_sport,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Inernet.name,
                iconId = R.drawable.ic_internet,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Transport.name,
                iconId = R.drawable.ic_transport,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Travel.name,
                iconId = R.drawable.ic_travel,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Charity.name,
                iconId = R.drawable.ic_charity,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Music.name,
                iconId = R.drawable.ic_music,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Service.name,
                iconId = R.drawable.ic_services,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Gift.name,
                iconId = R.drawable.ic_souvenirs,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Mobile.name,
                iconId = R.drawable.ic_mobile,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Home.name,
                iconId = R.drawable.ic_home,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Cinema.name,
                iconId = R.drawable.ic_cinema,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Car.name,
                iconId = R.drawable.ic_car,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Medicine.name,
                iconId = R.drawable.ic_medicine,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Education.name,
                iconId = R.drawable.ic_education,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Tv.name,
                iconId = R.drawable.ic_tv,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Wallet.name,
                iconId = R.drawable.ic_wallet,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Clothing.name,
                iconId = R.drawable.ic_clothing,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Finance.name,
                iconId = R.drawable.ic_finance,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Entertainment.name,
                iconId = R.drawable.ic_entertainment,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Other.name,
                iconId = R.drawable.ic_resource_else,
                id = UNDEFINED_ID.toLong(),
                description = "",
                colorRed =  colorRed,
                colorBlue = colorGreen,
                colorGreen = colorBlue
            )
        )

}