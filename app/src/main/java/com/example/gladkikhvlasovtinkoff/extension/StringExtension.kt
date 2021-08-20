package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.R

private val DIGITS_BEFORE_COMMA = 16
private val DIGITS_AFTER_COMMA = 6


fun String.convertToStyled() : String {
    val firstCommaIndex = this.getNumOfDigitsBeforeComma()
    val integerPart = this.getNumBeforeComma(firstCommaIndex)
    return integerPart.addDigitsAfterComma(firstCommaIndex, this)
}

private fun String.addDigitsAfterComma(commaIndex : Int, initialStr : String) : String =
    if(commaIndex < initialStr.length){
        buildString {
            append(this@addDigitsAfterComma)
            append(",")
            append(initialStr.substring(commaIndex + 1))
        }
    }
    else this

private fun String.getNumBeforeComma(commaIndex : Int) : String =
    buildString {
        var digitInLastGroup = 0
        for(i in commaIndex - 1 downTo 0 ) {
            if (digitInLastGroup < 3) {
                digitInLastGroup++
                append(this@getNumBeforeComma[i])
            }else{
                digitInLastGroup = 1
                append(" ")
                append(this@getNumBeforeComma[i])
            }
        }
    }.reversed()

private fun String.getNumOfDigitsBeforeComma() : Int {
    val first = this.indexOfFirst { char ->
        char == ','
    }
    return if (first != -1) first
    else this.length
}

fun String.convertFromStyled() : String =
    buildString {
        var containDot = false
        var currentBeforeComma = 0
        var currentAfterComma = 0
        for(char in this@convertFromStyled){
            if(char != ' ') {
                if(char == '.' || char == ',' ){
                    if(!containDot) {
                        containDot = true
                        append(',')
                    }
                }
                else if (!containDot && currentBeforeComma <= DIGITS_BEFORE_COMMA) {
                    append(char)
                    currentBeforeComma++
                }
                else if (containDot && currentAfterComma < DIGITS_AFTER_COMMA){
                    append(char)
                    currentAfterComma++
                }
            }
        }
    }

fun String.styleInput() = this.convertFromStyled().convertToStyled()

fun Boolean.getTransactionTypeString(context : Context) =
    if(this) context.getString(R.string.income_text)
    else context.getString(R.string.costs_text)