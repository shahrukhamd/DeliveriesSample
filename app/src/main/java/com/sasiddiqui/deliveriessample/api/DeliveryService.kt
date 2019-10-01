package com.sasiddiqui.deliveriessample.api

import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points
 */
interface DeliveryService {

    companion object {
        const val ENDPOINT = "https://mock-api-mobile.dev.lalamove.com/"
    }

    @GET("/deliveries/")
    suspend fun getDeliveries(@Query("offset") page: Int? = null,
                              @Query("limit") pageSize: Int? = null): Response<List<DeliveryItem>>
}