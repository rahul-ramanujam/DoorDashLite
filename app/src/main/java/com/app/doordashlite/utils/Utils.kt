package com.app.doordashlite.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun isPastClosingHours(input: String): Boolean {
        val current: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val diff = current.format(formatter).compareTo(input)
        return diff < 0
    }
}