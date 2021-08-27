package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder.Companion.AUTH_SHARED_PREF_KEY
import com.example.gladkikhvlasovtinkoff.model.UserBalanceInfo
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.exp

class UserBalanceInfoHolder @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        private const val USER_BALANCE_SHARED_PREF_KEY = "USER_BALANCE_SHARED_PREF_KEY"
        private const val EXPENSES_KEY = "EXPENSES_KEY"
        private const val INCOME_KEY = "INCOME_KEY"
    }

    private var dataHolder: SharedPreferences =
        context.getSharedPreferences(USER_BALANCE_SHARED_PREF_KEY, Context.MODE_PRIVATE)

    fun saveBalanceInfo(userBalanceInfo: UserBalanceInfo) {
        dataHolder.edit().apply {
            putString(EXPENSES_KEY, userBalanceInfo.expenses)
            putString(INCOME_KEY, userBalanceInfo.income)
        }.apply()
    }

    fun clearUserData() {
        dataHolder.edit().apply {
            putString(EXPENSES_KEY, "0")
            putString(INCOME_KEY, "0")
        }.apply()
    }

    fun getBalanceInfo(): UserBalanceInfo = UserBalanceInfo(
        expenses = dataHolder.getString(EXPENSES_KEY, "0") ?: "0",
        income = dataHolder.getString(INCOME_KEY, "0") ?: "0"
    )
}