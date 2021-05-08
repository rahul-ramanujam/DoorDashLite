package com.app.doordashlite.data

import com.squareup.moshi.Json

data class FeedResponse(
    @Json(name = "num_results") val numOfResults:Int,
    @Json(name = "stores") val stores: List<Store>)
