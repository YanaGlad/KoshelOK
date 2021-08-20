package com.example.gladkikhvlasovtinkoff.model

import com.example.gladkikhvlasovtinkoff.data.model.OperationCategory
import com.example.gladkikhvlasovtinkoff.data.model.OperationCategoryData

interface OperationCategoryDataFactory {
    fun getOperationCategoryData(category : OperationCategory) : OperationCategoryData
    fun getCategories() : List<OperationCategoryData>
}
