package com.example.gladkikhvlasovtinkoff.extension

fun String.styleText() : String {
    var result = ""
    for (i in this.indices.reversed()) {
        result += this[i]
        if ((i - this.length) % 3 == 0) {
            result+=" "
        }
    }
    return result.reversed() + " ₽"
}