package com.example.gladkikhvlasovtinkoff.model

data class WalletData (
    val id : Long,
    val userId : Long,
    val name : String,
    val limit : Long,
    val amount : Long,
    val currency : Currency
    )

