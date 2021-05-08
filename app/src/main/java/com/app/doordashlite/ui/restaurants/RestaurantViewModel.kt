package com.app.doordashlite.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.doordashlite.data.Store
import com.app.doordashlite.network.DoorDashApi
import com.app.doordashlite.network.RestaurantService
import com.app.doordashlite.utils.Resource
import kotlinx.coroutines.launch

class RestaurantViewModel(private val restaurantService: RestaurantService = DoorDashApi.restaurantService) :
    ViewModel() {

    private val _response = MutableLiveData<Resource<List<Store>>>()

    val response: LiveData<Resource<List<Store>>>
        get() = _response

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            _response.value = Resource(Resource.Status.LOADING)
            try {
                 val stores = restaurantService.getRestaurants(37.422740, -122.139956).stores
                _response.value = Resource(Resource.Status.SUCCESS, stores)
            } catch (e: Exception) {
                _response.value = Resource(Resource.Status.ERROR, emptyList(), "error loading restaurants")
            }
        }
    }
}