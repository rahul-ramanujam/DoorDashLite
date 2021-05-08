package com.app.doordashlite.repository

import com.app.doordashlite.data.Store
import com.app.doordashlite.network.DoorDashApi
import com.app.doordashlite.network.RestaurantService

/**
 * Repository for restaurants, DAO can be added/injected to support caching
 */
class RestaurantRepository(private val service: RestaurantService = DoorDashApi.restaurantService) {

    suspend fun getStores(lat: Double, lng: Double): List<Store> {
        return service.getRestaurants(lat, lng).stores
    }
}