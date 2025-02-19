package com.natio21.nocoiner_control

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    fun getHour(unixTime: Long): Int {
        val instant = Instant.ofEpochSecond(unixTime)
        val formatter = DateTimeFormatter.ofPattern("HH")
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant).toInt()
    }

    fun convertUnixTimeToReadable(unixTime: Long): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val formatter = DateTimeFormatter.ofPattern("HH")
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}