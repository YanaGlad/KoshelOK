package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import com.example.gladkikhvlasovtinkoff.model.CurrencyCourse

sealed class CoursesPlateViewState {
    object Loading : CoursesPlateViewState()
    class Loaded(val currencyCourses : List<CurrencyCourse>) : CoursesPlateViewState()
    object Error : CoursesPlateViewState()
}