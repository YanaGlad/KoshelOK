package com.example.gladkikhvlasovtinkoff.auth

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthDataHolder @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val AUTH_SHARED_PREF_KEY = "AUTH_SHARED_PREF"
        const val USERNAME_KEY = "USERNAME_KEY"
        const val GOOGLE_ID_KEY = "GOOGLE_ID_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
    }

    private var dataHolder: SharedPreferences =
        context.getSharedPreferences(AUTH_SHARED_PREF_KEY, Context.MODE_PRIVATE)

    fun parseAccountInfo(account: GoogleSignInAccount) {
        dataHolder.edit().apply {
            putString(USERNAME_KEY, account.displayName)
            putString(GOOGLE_ID_KEY, account.id)
            putString(EMAIL_KEY, account.email)
        }.apply()
    }

    fun clearUserData() {
        dataHolder.edit().apply {
            putString(USERNAME_KEY, "")
            putString(GOOGLE_ID_KEY, "")
            putString(EMAIL_KEY, "")
        }.apply()
    }

    fun isAuth(): Boolean =
        dataHolder.getString(EMAIL_KEY, "") != ""

    fun isUserChangeAccount(account: GoogleSignInAccount): Boolean {
        val oldKey = getAccountKey(account)
        return oldKey != getUserKey()
    }

    fun getAccountKey(account: GoogleSignInAccount) = account.email!!

    fun getUserKey(): String =
        dataHolder.getString(EMAIL_KEY, "") ?: ""
}
