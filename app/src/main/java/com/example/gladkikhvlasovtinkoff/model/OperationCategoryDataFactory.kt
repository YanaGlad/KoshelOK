package com.example.gladkikhvlasovtinkoff.model

interface OperationCategoryDataFactory {
    fun getOperationCategoryData(category : OperationCategory) : OperationCategoryData
    fun getCategories() : List<OperationCategoryData>
}
