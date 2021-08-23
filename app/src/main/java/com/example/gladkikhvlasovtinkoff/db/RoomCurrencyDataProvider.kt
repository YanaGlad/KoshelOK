package com.example.gladkikhvlasovtinkoff.db

import javax.inject.Inject

class RoomCurrencyDataProvider @Inject constructor(val dao : CurrencyDao)
    : LocalCurrencyDataProvider{

}