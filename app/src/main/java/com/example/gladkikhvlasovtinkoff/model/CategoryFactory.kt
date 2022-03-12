package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.gladkikhvlasovtinkoff.R
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

enum class UserCateroryKeys {
    Supermarket, Avia, Jewelery, Restaurants, Pharmancy, Cafe, Connection, Theatre, Train,
    Pets, Sport, Inernet, Transport, Medicine,
    Travel, Charity, Music, Service, Gift, Mobile, Home, Cinema, Car,
    Education, Tv, Wallet, Clothing, Finance, Entertainment, Other
}

class CategoryFactory(activity: AppCompatActivity) : TransactionCategoryDataFactory {
    var colorRed: Int = 89
        set(value) {
            field = value
        }

    var colorGreen: Int = 77
        set(value) {
            field = value
        }

    var colorBlue: Int = 244
        set(value) {
            field = value
        }

    var color: Int = ColorPicker(activity, colorRed, colorGreen, colorBlue).color
        set(value) {
            field = value
        }

    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = UserCateroryKeys.Supermarket.name,
                iconId = R.drawable.ic_supermarket,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Avia.name,
                iconId = R.drawable.ic_avia,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Jewelery.name,
                iconId = R.drawable.ic_jewelry,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Restaurants.name,
                iconId = R.drawable.ic_restaurants_user,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Pharmancy.name,
                iconId = R.drawable.ic_pharmacy,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                income = true,
                id = UNDEFINED_ID.toLong()
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Cafe.name,
                iconId = R.drawable.ic_restaurants,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Connection.name,
                iconId = R.drawable.ic_svyaz,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Theatre.name,
                iconId = R.drawable.ic_theatre,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Train.name,
                iconId = R.drawable.ic_train,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Pets.name,
                iconId = R.drawable.ic_pets,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Sport.name,
                iconId = R.drawable.ic_sport,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Inernet.name,
                iconId = R.drawable.ic_internet,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Transport.name,
                iconId = R.drawable.ic_transport,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                income =  true,
                id = UNDEFINED_ID.toLong()
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Travel.name,
                iconId = R.drawable.ic_travel,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Charity.name,
                iconId = R.drawable.ic_charity,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Music.name,
                iconId = R.drawable.ic_music,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Service.name,
                iconId = R.drawable.ic_services,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Gift.name,
                iconId = R.drawable.ic_souvenirs,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Mobile.name,
                iconId = R.drawable.ic_mobile,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Home.name,
                iconId = R.drawable.ic_home,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Cinema.name,
                iconId = R.drawable.ic_cinema,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Car.name,
                iconId = R.drawable.ic_car,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Medicine.name,
                iconId = R.drawable.ic_medicine,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Education.name,
                iconId = R.drawable.ic_education,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Tv.name,
                iconId = R.drawable.ic_tv,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Wallet.name,
                iconId = R.drawable.ic_wallet,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Clothing.name,
                iconId = R.drawable.ic_clothing,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Finance.name,
                iconId = R.drawable.ic_finance,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Entertainment.name,
                iconId = R.drawable.ic_entertainment,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                income =   true,
                id = UNDEFINED_ID.toLong()
            ),
            TransactionCategoryData(
                name = UserCateroryKeys.Other.name,
                iconId = R.drawable.ic_resource_else,
                userName = UNDEFINED_STR,
                description = "",
                colorRed = colorRed,
                colorBlue = colorBlue,
                colorGreen = colorGreen,
                id = UNDEFINED_ID.toLong(),
                income = true
            )
        )
}
