package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

enum class IconNameId{
    Salary, PartTime, Gift, Capitalization,
    Restaurants, Supermarkets, Sports, PublicTransport,
    Pharmacy, GasStation, Rent, Travel
}

fun getIconIdByNameId(nameId : String) : Int =
    when(nameId) {
        IconNameId.Salary.name, IconNameId.PartTime.name -> R.drawable.ic_salary
        IconNameId.Gift.name -> R.drawable.ic_gift
        IconNameId.Capitalization.name -> R.drawable.ic_capitalisation
        IconNameId.Restaurants.name -> R.drawable.ic_restaurants
        IconNameId.Supermarkets.name -> R.drawable.ic_supermarket
        IconNameId.Sports.name -> R.drawable.ic_sport
        IconNameId.PublicTransport.name -> R.drawable.ic_public_transport
        IconNameId.Pharmacy.name -> R.drawable.ic_pharmacy
        IconNameId.GasStation.name -> R.drawable.ic_gas_station
        IconNameId.Rent.name -> R.drawable.ic_rent
        IconNameId.Travel.name -> R.drawable.ic_travel
        else -> throw java.lang.IllegalArgumentException("wrong string id")
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