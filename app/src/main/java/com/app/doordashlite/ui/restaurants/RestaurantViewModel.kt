package com.app.doordashlite.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.doordashlite.data.Store
import com.app.doordashlite.utils.Resource
import kotlinx.coroutines.launch

class RestaurantViewModel() : ViewModel() {

    private val _response = MutableLiveData<Resource<List<Store>>>()

    val response: LiveData<Resource<List<Store>>>
        get() = _response


    fun getRestaurants() {
        viewModelScope.launch {

        }
    }
}