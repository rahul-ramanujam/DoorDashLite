package com.app.doordashlite.ui.restaurants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.doordashlite.R
import com.app.doordashlite.utils.Resource
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantListFragment : Fragment() {

    private val viewModel:RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.response.observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> progressBar.visibility = View.VISIBLE
                Resource.Status.SUCCESS -> {
                    it.data?.let { stores ->
                        progressBar.visibility = View.GONE
                        restaurantList.visibility =View.VISIBLE
                        restaurantList.adapter = RestaurantAdapter(stores)
                    }
                }
                Resource.Status.ERROR -> Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
            }

        })
    }
}