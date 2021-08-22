package com.example.gladkikhvlasovtinkoff.ui.ui.delegates

import com.example.gladkikhvlasovtinkoff.model.TransactionDate

data class DateDelegateItem(
    val value : TransactionDate
): DelegateItem {
    override fun content(): Any = value

    override fun id(): Any = value.hashCode()
}
