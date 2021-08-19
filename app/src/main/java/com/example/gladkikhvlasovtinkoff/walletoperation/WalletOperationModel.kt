package com.example.gladkikhvlasovtinkoff.walletoperation

data class WalletOperationModel (
    val date : Long,
    val imageId : Int,
    val categoryTextId : Int,
    val type : String,
    val value : String,
    )