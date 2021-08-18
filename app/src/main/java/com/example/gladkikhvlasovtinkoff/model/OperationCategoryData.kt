package com.example.gladkikhvlasovtinkoff.model

class OperationCategoryData (
    val type : OperationCategory,
    val nameId : Int,
    val iconId : Int
    )

sealed class OperationCategory{
    object Salary : OperationCategory()
    object PartTime : OperationCategory()
    object Gift : OperationCategory()
    object Capitalization : OperationCategory()
}
