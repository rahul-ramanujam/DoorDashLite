package com.app.doordashlite.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RestaurantServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: RestaurantService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RestaurantService::class.java)
    }

    @Test
    fun requestRestaurants() {
        runBlocking{
            enqueueResponse("restaurant_feed.json")
            val resultResponse = service.getRestaurants(2.0,3.0)
            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/v1/store_feed?lat=2.0&lng=3.0&offset=0&limit=50"))
        }
    }

    @Test
    fun getRestaurantResponseSize() {
        runBlocking{
            enqueueResponse("restaurant_feed.json")
            val resultResponse = service.getRestaurants(2.0,3.0)
            val stores = resultResponse.stores

            Assert.assertThat(stores.size, CoreMatchers.`is`(4))
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}