package com.app.doordashlite.ui.restaurants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.doordashlite.R
import com.app.doordashlite.utils.Result
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantListFragment : Fragment() {

    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.response.observe(viewLifecycleOwner, {
            when (it.status) {
                Result.Status.LOADING -> progressBar.visibility = View.VISIBLE
                Result.Status.SUCCESS -> {
                    it.data?.let { stores ->
                        progressBar.visibility = View.GONE
                        restaurantList.visibility = View.VISIBLE
                        restaurantList.adapter = RestaurantAdapter(stores)
                    }
                }
                Result.Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                    activity?.finish()
                }

            }

        })
        viewModel.getRestaurants(37.422740, -122.139956)
    }
}