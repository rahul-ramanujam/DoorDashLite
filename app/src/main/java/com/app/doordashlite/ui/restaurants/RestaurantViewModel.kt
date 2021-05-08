package com.app.doordashlite.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.doordashlite.data.Store
import com.app.doordashlite.repository.RestaurantRepository
import com.app.doordashlite.utils.Result
import com.app.doordashlite.utils.Result.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel(private val repository: RestaurantRepository = RestaurantRepository()) :
    ViewModel() {

    private val _response = MutableLiveData<Result<List<Store>>>()

    val response: LiveData<Result<List<Store>>>
        get() = _response

    fun getRestaurants(lat: Double?, lng: Double?) {
        if (lat == null || lng == null) {
            _response.value = Result(ERROR, emptyList(), "Input Error: Lat & Long can't be null")
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            _response.value = Result(LOADING)
            try {
                val stores = repository.getStores(lat, lng)
                _response.value = Result(SUCCESS, stores)
            } catch (e: Exception) {
                _response.value =
                    Result(ERROR, emptyList(), "error loading restaurants")
            }
        }
    }
}