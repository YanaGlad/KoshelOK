package com.example.gladkikhvlasovtinkoff.network.wallet

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_ID
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR

class IconMapper {

    companion object {
        private const val SALARY = "salary"
        private const val PART_TIME = "part-time"
        private const val GIFT = "gift"
        private const val CAPITALIZATION = "capitalization"
        private const val RESTAURANTS = "restaurants"
        private const val SUPERMARKETS = "supermarkets"
        private const val SPORTS = "sports"
        private const val PUBLIC_TRANSPORT = "public-transport"
        private const val PHARMACY = "pharmacy"
        private const val GAS_STATION = "gas-station"
        private const val RENT = "rent"
        private const val TRAVEL = "travel"
    }

    fun getIconIdByName(name : String) : Int =
        when(name) {
            SALARY, PART_TIME -> R.drawable.ic_salary
            GIFT -> R.drawable.ic_gift
            CAPITALIZATION -> R.drawable.ic_capitalisation
            RESTAURANTS -> R.drawable.ic_restaurants
            SUPERMARKETS -> R.drawable.ic_supermarket
            SPORTS -> R.drawable.ic_sport
            PUBLIC_TRANSPORT -> R.drawable.ic_public_transport
            PHARMACY -> R.drawable.ic_pharmacy
            GAS_STATION -> R.drawable.ic_gas_station
            RENT -> R.drawable.ic_rent
            TRAVEL -> R.drawable.ic_travel
            else -> UNDEFINED_ID
        }

    fun getIdentifierForCategoryName(context : Context, name : String) : String =
        when(name){
            context.getString(R.string.salary) -> SALARY
            context.getString(R.string.part_time) -> PART_TIME
            context.getString(R.string.gift) -> GIFT
            context.getString(R.string.capitalization) -> CAPITALIZATION
            context.getString(R.string.restaurants) -> RESTAURANTS
            context.getString(R.string.supermarket) -> SUPERMARKETS
            context.getString(R.string.sport) -> SPORTS
            context.getString(R.string.public_transport) -> PUBLIC_TRANSPORT
            context.getString(R.string.pharmacy) -> PHARMACY
            context.getString(R.string.gas_station) -> GAS_STATION
            context.getString(R.string.rent) -> RENT
            context.getString(R.string.travel) -> TRAVEL
            else -> UNDEFINED_STR
        }
}