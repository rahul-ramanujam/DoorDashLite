package com.app.doordashlite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.doordashlite.R
import com.app.doordashlite.ui.restaurants.RestaurantListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view,
                RestaurantListFragment()
            )
            .commit()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}