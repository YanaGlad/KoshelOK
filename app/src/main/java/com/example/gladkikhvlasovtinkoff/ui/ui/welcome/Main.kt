package com.example.gladkikhvlasovtinkoff.ui.ui.welcome

import java.util.*

fun main(){
    val a = intArrayOf(5, 5)
    var b = 1
    a[b] = 0.also { b = it }
    System.out.println(Arrays.toString(a))
}