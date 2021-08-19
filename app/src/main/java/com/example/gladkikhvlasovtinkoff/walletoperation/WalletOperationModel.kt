package com.example.gladkikhvlasovtinkoff.walletoperation

data class WalletOperationModel (
    val date : String,
    val image : String,
    val title : String,
    val subtitle : String,
    val money : String,
    val time : String
        ){
    var isVisible : Boolean = true
}