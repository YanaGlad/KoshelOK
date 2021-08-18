package com.example.gladkikhvlasovtinkoff

class EasyTimer {
    private var startTime = 0.0
     val SECOND = 1000000000.0

    var passedTime = 0.0
        private set
    var stop = false

    fun startTimer() {
        stop = false
        startTime = System.nanoTime() / SECOND
    }

    fun timerDelay(second: Double): Boolean {
        if (!stop) passedTime = System.nanoTime() / SECOND - startTime
        return passedTime > second
    }

    fun stopTimer() {
        stop = true
    }
}