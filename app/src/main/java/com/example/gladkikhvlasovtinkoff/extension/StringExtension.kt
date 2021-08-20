package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

fun String.convertToStyled() : String {
    val firstIndex = this.indexOfFirst{ char ->
        char == ','
    }
    val firstCommaIndex = if(firstIndex != -1) firstIndex
    else this.length
    var digitInLastGroup = 0
    val integerPart = buildString {
        for(i in firstCommaIndex - 1 downTo 0 ) {
            if (digitInLastGroup < 3) {
                digitInLastGroup++
                append(this@convertToStyled[i])
            }else{
                digitInLastGroup = 1
                append(" ")
                append(this@convertToStyled[i])
            }
        }
    }.reversed()
    return if(firstCommaIndex < length){
        buildString {
            append(integerPart)
            append(",")
            append(this@convertToStyled.substring(firstCommaIndex + 1))
        }
    }
    else integerPart
}

fun String.convertFromStyled() : String =
    buildString {
        var containDot = false
        for(char in this@convertFromStyled){
            if(char != ' ') {
                if(char == '.' || char == ',' && !containDot) {
                    containDot = true
                    append(',')
                }
                else {
                    append(char)
                }
            }
        }
    }

fun String.styleInput() = this.convertFromStyled().convertToStyled()

fun Boolean.getTransactionTypeString(context : Context) =
    if(this) context.getString(R.string.income_text)
    else context.getString(R.string.costs_text)