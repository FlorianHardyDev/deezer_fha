package com.fha.deezer.utils

import kotlin.math.floor

class DurationHelper {
    /**
     * Format a String duration (in seconds) to HH:mm:ss
     *
     * @seconds the number of seconds to format
     * @return HH:mm:ss
     */
    fun formatSeconds(seconds: String): String? {
        return try {
            val timeInSeconds = seconds.toInt()
            val secondsLeft = timeInSeconds % 3600 % 60
            val minutes = floor(timeInSeconds % 3600 / 60.toDouble()).toInt()
            val hours = floor(timeInSeconds / 3600.toDouble()).toInt()
            val pHours = (if (hours < 10) "0" else "") + hours
            val pMinutes = (if (minutes < 10) "0" else "") + minutes
            val pSeconds = (if (secondsLeft < 10) "0" else "") + secondsLeft
            "$pHours:$pMinutes:$pSeconds"
        } catch (e: Exception) {
            ""
        }
    }
}