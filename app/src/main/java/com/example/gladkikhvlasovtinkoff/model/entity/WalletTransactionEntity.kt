package com.example.gladkikhvlasovtinkoff.model.entity

//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.Ignore
//import androidx.room.PrimaryKey
//import com.example.gladkikhvlasovtinkoff.model.Currency
//import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
//import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_ID
//import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR
//import com.google.gson.annotations.SerializedName
//
//@Entity(tableName = "transaction_table")
//data class WalletTransactionEntity (
//
//    @PrimaryKey
//    @field:SerializedName("id")
//    val id : Int = -1,
//
//    @field:SerializedName("date")
//    val date : Long = UNDEFINED_ID.toLong(),
//
//    @field:SerializedName("wallet_id")
//    val walletId : Long = UNDEFINED_ID.toLong(),
//
//    @field:SerializedName("is_income")
//    val isIncome : Boolean = false,
//
//    @field:SerializedName("amount")
//    val amount : String = UNDEFINED_STR,
//
//    @Embedded
//    @field:SerializedName("currency")
//    val currency: CurrencyEntity = CurrencyEntity(UNDEFINED_STR, UNDEFINED_STR),
//
//    @Ignore
//    @Embedded
//    @field:SerializedName("transaction_category_data")
//    val transactionCategoryData: TransactionCategoryData = TransactionCategoryData(
//        UNDEFINED_STR,
//        UNDEFINED_ID, UNDEFINED_ID, UNDEFINED_STR
//    )
//){
//
//    val isValid
//        get() =
//            date != UNDEFINED_ID.toLong() && walletId != UNDEFINED_ID.toLong() &&
//                    amount != UNDEFINED_STR && currency.code != UNDEFINED_STR &&
//                    currency.name != UNDEFINED_STR && transactionCategoryData.iconId != UNDEFINED_ID
//                    && transactionCategoryData.name != UNDEFINED_STR
//}