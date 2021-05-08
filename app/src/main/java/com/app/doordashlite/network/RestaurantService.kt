package com.app.doordashlite.network

import com.app.doordashlite.data.FeedResponse
import com.app.doordashlite.data.Store
import com.app.doordashlite.data.StoreDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantService {
    @GET("v1/store_feed")
    suspend fun getRestaurants(
        @Query(value = "lat") lat: Double,
        @Query(value = "lng") lng: Double,
        @Query(value = "offset") offset: Int = 0,
        @Query(value = "limit") limit: Int = 50
    ): FeedResponse

    @GET("v2/restaurant/{id}")
    fun getRestaurantDetail(@Path(value = "id") id: Int): StoreDetail
}