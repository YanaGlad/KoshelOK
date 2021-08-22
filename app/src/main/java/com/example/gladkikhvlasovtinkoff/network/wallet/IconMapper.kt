package com.example.gladkikhvlasovtinkoff.network.wallet

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

class IconMapper {

    enum class IconNameId{
        Salary, PartTime, Gift, Capitalization,
        Restaurants, Supermarkets, Sports, PublicTransport,
        Pharmacy, GasStation, Rent, Travel
    }

    fun getIconIdByNameId(nameId : IconNameId) : Int =
        when(nameId) {
            IconNameId.Salary, IconNameId.PartTime -> R.drawable.ic_salary
            IconNameId.Gift -> R.drawable.ic_gift
            IconNameId.Capitalization -> R.drawable.ic_capitalisation
            IconNameId.Restaurants -> R.drawable.ic_restaurants
            IconNameId.Supermarkets -> R.drawable.ic_supermarket
            IconNameId.Sports -> R.drawable.ic_sport
            IconNameId.PublicTransport -> R.drawable.ic_public_transport
            IconNameId.Pharmacy -> R.drawable.ic_pharmacy
            IconNameId.GasStation -> R.drawable.ic_gas_station
            IconNameId.Rent -> R.drawable.ic_rent
            IconNameId.Travel -> R.drawable.ic_travel
        }

    fun getIdentifierForCategoryName(context : Context, name : String) : IconNameId =
        when(name){
            context.getString(R.string.salary) -> IconNameId.Salary
            context.getString(R.string.part_time) -> IconNameId.PartTime
            context.getString(R.string.gift) -> IconNameId.Gift
            context.getString(R.string.capitalization) -> IconNameId.Capitalization
            context.getString(R.string.restaurants) -> IconNameId.Restaurants
            context.getString(R.string.supermarket) -> IconNameId.Supermarkets
            context.getString(R.string.sport) -> IconNameId.Sports
            context.getString(R.string.public_transport) -> IconNameId.PublicTransport
            context.getString(R.string.pharmacy) -> IconNameId.Pharmacy
            context.getString(R.string.gas_station) -> IconNameId.GasStation
            context.getString(R.string.rent) -> IconNameId.Rent
            context.getString(R.string.travel) -> IconNameId.Travel
            else -> throw IllegalArgumentException("cannot convert this name to category identifier")
        }
}