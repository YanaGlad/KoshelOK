package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

enum class IconNameId {
    Salary, PartTime, Capitalization, Supermarkets, Sports, PublicTransport,
    Pharmacy, GasStation, Rent, Travel, Avia,
    Jewelery, Restaurants, Cafe, Connection, Theatre, Train,
    Pets, Sport, Itnernet, Transport, Medicine,
      Charity, Music, Service, Gift, Mobile, Home, Cinema, Car,
    Education, Tv, Wallet, Clothing, Finance, Entertainment, Other
}

fun getIconIdByNameId(nameId: String): Int =
    when (nameId) {
        IconNameId.Salary.name, IconNameId.PartTime.name -> R.drawable.ic_salary
        IconNameId.Gift.name -> R.drawable.ic_gift
        IconNameId.Capitalization.name -> R.drawable.ic_capitalisation
        IconNameId.Restaurants.name -> R.drawable.ic_restaurants_user
        IconNameId.Supermarkets.name -> R.drawable.ic_supermarket
        IconNameId.Sports.name -> R.drawable.ic_sport
        IconNameId.PublicTransport.name -> R.drawable.ic_public_transport
        IconNameId.Pharmacy.name -> R.drawable.ic_pharmacy
        IconNameId.GasStation.name -> R.drawable.ic_gas_station
        IconNameId.Rent.name -> R.drawable.ic_rent
        IconNameId.Travel.name -> R.drawable.ic_travel
        IconNameId.Avia.name -> R.drawable.ic_avia
        IconNameId.Jewelery.name -> R.drawable.ic_jewelry
        IconNameId.Cafe.name -> R.drawable.ic_restaurants
        IconNameId.Connection.name -> R.drawable.ic_svyaz
        IconNameId.Theatre.name -> R.drawable.ic_theatre
        IconNameId.Train.name -> R.drawable.ic_train
        IconNameId.Pets.name -> R.drawable.ic_pets
        IconNameId.Sport.name -> R.drawable.ic_sport
        IconNameId.Itnernet.name -> R.drawable.ic_internet
        IconNameId.Transport.name -> R.drawable.ic_transport
        IconNameId.Medicine.name -> R.drawable.ic_medicine
        IconNameId.Charity.name -> R.drawable.ic_charity
        IconNameId.Music.name -> R.drawable.ic_music
        IconNameId.Service.name -> R.drawable.ic_services
        IconNameId.Home.name -> R.drawable.ic_home
        IconNameId.Cinema.name -> R.drawable.ic_cinema
        IconNameId.Car.name -> R.drawable.ic_car
        IconNameId.Cinema.name -> R.drawable.ic_cinema
        IconNameId.Education.name -> R.drawable.ic_education
        IconNameId.Tv.name -> R.drawable.ic_tv
        IconNameId.Wallet.name -> R.drawable.ic_wallet
        IconNameId.Clothing.name -> R.drawable.ic_clothing
        IconNameId.Entertainment.name -> R.drawable.ic_entertainment
        IconNameId.Other.name -> R.drawable.ic_resource_else
        IconNameId.Finance.name -> R.drawable.ic_finance


        else -> R.drawable.ic_finance
    }

fun getIdentifierForCategoryName(context: Context, name: String): IconNameId =
    when (name) {
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
        else -> IconNameId.Salary
    }