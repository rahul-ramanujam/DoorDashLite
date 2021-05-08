package com.app.doordashlite.data

import com.squareup.moshi.Json

data class Store(
    val name: String,
    @Json(name = "cover_img_url") val coverImageUrl: String,
    @Json(name = "header_img_url") val headerImageUrl: String,
    @Json(name = "next_close_time") val closeTime: String,
    @Json(name = "next_open_time") val openTime: String,
    val description: String,
    val status: Status
)
