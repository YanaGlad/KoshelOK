package com.example.gladkikhvlasovtinkoff.model

import com.example.gladkikhvlasovtinkoff.R

class OperationCategoryDataFactoryImpl : OperationCategoryDataFactory {
    override fun getOperationCategoryData(category: OperationCategory): OperationCategoryData =
        when(category){
            is OperationCategory.Capitalization ->
                OperationCategoryData(
                    type = OperationCategory.Capitalization,
                    iconId = R.drawable.ic_capitalisation,
                    nameId = R.string.capitalization
                )
            is OperationCategory.Gift ->
                OperationCategoryData(
                    type = OperationCategory.Gift,
                    iconId = R.drawable.ic_gift,
                    nameId = R.string.gift
                )
            is OperationCategory.PartTime ->
                OperationCategoryData(
                    type = OperationCategory.PartTime,
                    iconId = R.drawable.ic_salary,
                    nameId = R.string.part_time
                )
            is OperationCategory.Salary ->
                OperationCategoryData(
                    type = OperationCategory.Capitalization,
                    iconId = R.drawable.ic_salary,
                    nameId = R.string.salary
                )

    }

    override fun getCategories(): List<OperationCategoryData> =
        listOf(
            getOperationCategoryData(
                OperationCategory.Salary),
            getOperationCategoryData(
                OperationCategory.PartTime),
            getOperationCategoryData(
                OperationCategory.Gift),
            getOperationCategoryData(
                OperationCategory.Capitalization))

}
