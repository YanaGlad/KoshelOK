package com.example.gladkikhvlasovtinkoff.extension

import com.example.gladkikhvlasovtinkoff.model.Currency


fun List<Currency>.styleListToAppropriateForm() : List<Currency>{
    val codeList = listOf("RUB", "USD", "EUR")
    val styledList = mutableListOf<Currency>()
    for(item in codeList){
        val toAdd = this.firstOrNull{
            it.code == item
        }
        if(toAdd != null)
            styledList.add(toAdd)
    }
    for(item in this){
        if(item !in styledList){
            styledList.add(item)
        }
    }
    return styledList
}