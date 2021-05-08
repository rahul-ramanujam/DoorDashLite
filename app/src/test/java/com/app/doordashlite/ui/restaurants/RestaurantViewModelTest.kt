package com.app.doordashlite.ui.restaurants

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.doordashlite.repository.RestaurantRepository
import com.app.doordashlite.utils.Result
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.bouncycastle.util.Store
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RestaurantViewModelTest {

    private val repository = Mockito.mock(RestaurantRepository::class.java)
    private val viewModel = RestaurantViewModel(repository)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testNull() {
        testDispatcher.runBlockingTest {
            viewModel.getRestaurants(null, null)
            assertEquals(viewModel.response.value, Result<List<Store>>(Result.Status.ERROR, emptyList(), "Input Error: Lat & Long can't be null"))
            Mockito.verify(repository, Mockito.never())
        }
    }

    @Test
    fun testApiCall() {
        testDispatcher.runBlockingTest {
            viewModel.getRestaurants(2.0, 3.0)
            Mockito.verify(repository, Mockito.atLeastOnce()).getStores(2.0, 3.0)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}