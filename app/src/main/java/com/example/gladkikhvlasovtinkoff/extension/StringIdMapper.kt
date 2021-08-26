package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

fun getNameIdByStringId(stringId : String, context: Context) : String =
    when(stringId) {
        IconNameId.Salary.name -> context.getString(R.string.salary)
        IconNameId.PartTime.name -> context.getString(R.string.part_time)
        IconNameId.Gift.name -> context.getString(R.string.gift)
        IconNameId.Capitalization.name -> context.getString(R.string.capitalization)
        IconNameId.Restaurants.name -> context.getString(R.string.restaurants)
        IconNameId.Supermarkets.name -> context.getString(R.string.supermarket)
        IconNameId.Sports.name -> context.getString(R.string.sport)
        IconNameId.PublicTransport.name -> context.getString(R.string.public_transport)
        IconNameId.Pharmacy.name -> context.getString(R.string.pharmacy)
        IconNameId.GasStation.name -> context.getString(R.string.gas_station)
        IconNameId.Rent.name -> context.getString(R.string.rent)
        IconNameId.Travel.name -> context.getString(R.string.travel)
        else -> throw java.lang.IllegalArgumentException("wrong string id")
    }