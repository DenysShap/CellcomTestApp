package com.example.data.converter

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeConverter {
    fun formattedYear(releaseDate: String?): String? {
        return try {
            releaseDate?.let {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val outputFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
                inputFormat.parse(it)?.let { it1 -> outputFormat.format(it1) }
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun minuteToTime(min: Int): String {
        val hours = (min / 60) % 12
        val minutes = min % 60
        val hoursFormatted = if (hours == 0) "12" else hours.toString()
        val minutesFormatted = minutes.toString().padStart(2, '0')
        return "${hoursFormatted}h ${minutesFormatted}m"
    }
}