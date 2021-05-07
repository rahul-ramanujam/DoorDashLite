package com.app.doordashlite.data

import com.squareup.moshi.Json

data class StoreDetail(
    val id: String,
    @Json(name = "phone_number") val phoneNumber: String,
    val description: String
)
