package com.example.gladkikhvlasovtinkoff.model

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R


enum class userCateroryKeys {
    Supermarket, Avia, Jewelery, Restaurants, Pharmancy, Cafe, Connection, Theatre, Train, Pets, Sport, Inernet, Transport, Medicine,
    Travel, Charity, Music, Service, Gift, Mobile,  Home, Cinema, Car, Education, Tv, Wallet, Clothing, Finance, Entertainment, Other
}

class CategoryFactory : TransactionCategoryDataFactory {

    override fun getCategories(context: Context): List<TransactionCategoryData> =
        listOf(
            TransactionCategoryData(
                name = userCateroryKeys.Supermarket.name,
                iconId = R.drawable.ic_supermarket,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Avia.name ,
                iconId = R.drawable.ic_avia,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Jewelery.name,
                iconId = R.drawable.ic_jewelry,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Restaurants.name,
                iconId = R.drawable.ic_restaurants_2,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Pharmancy.name,
                iconId = R.drawable.ic_pharmacy,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Cafe.name,
                iconId = R.drawable.ic_restaurants,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Connection.name,
                iconId = R.drawable.ic_svyaz,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Theatre.name,
                iconId = R.drawable.ic_theatre,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Train.name,
                iconId = R.drawable.ic_train,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Pets.name,
                iconId = R.drawable.ic_pets,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Sport.name,
                iconId = R.drawable.ic_sport,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Inernet.name,
                iconId = R.drawable.ic_internet,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Transport.name,
                iconId = R.drawable.ic_transport,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Travel.name,
                iconId = R.drawable.ic_travel,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Charity.name,
                iconId = R.drawable.ic_charity,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Music.name,
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
                name = userCateroryKeys.Gift.name,
                iconId = R.drawable.ic_souvenirs,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Mobile.name,
                iconId = R.drawable.ic_mobile,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Home.name,
                iconId = R.drawable.ic_home,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Cinema.name,
                iconId = R.drawable.ic_cinema,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Car.name,
                iconId = R.drawable.ic_car,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Medicine.name,
                iconId = R.drawable.ic_medicine,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Education.name,
                iconId = R.drawable.ic_education,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Tv.name,
                iconId = R.drawable.ic_tv,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Wallet.name,
                iconId = R.drawable.ic_wallet,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Clothing.name,
                iconId = R.drawable.ic_clothing,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Finance.name,
                iconId = R.drawable.ic_finance,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Entertainment.name,
                iconId = R.drawable.ic_entertainment,
                id = UNDEFINED_ID,
                description = ""
            ),
            TransactionCategoryData(
                name = userCateroryKeys.Other.name,
                iconId = R.drawable.ic_resource_else,
                id = UNDEFINED_ID,
                description = ""
            )
        )

}