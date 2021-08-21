package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager


class CustomGridLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private var isScrollEnabled = false

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled
    }
}