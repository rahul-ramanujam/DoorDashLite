package com.app.doordashlite.data

import com.squareup.moshi.Json

data class Status(
    @Json(name = "asap_minutes_range")
    val range_minutes: List<Int>,
    @Json(name = "pickup_available")
    val isPickUpAvailable: Boolean
)
