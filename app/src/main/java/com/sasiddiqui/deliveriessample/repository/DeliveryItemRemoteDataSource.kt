package com.sasiddiqui.deliveriessample.repository

import com.sasiddiqui.deliveriessample.api.BaseDataSource
import com.sasiddiqui.deliveriessample.api.DeliveryService
import javax.inject.Inject

/**
 * Works with the API to get data.
 */
class DeliveryItemRemoteDataSource @Inject constructor(private val service: DeliveryService) : BaseDataSource() {

    suspend fun fetchData(offset: Int, pageSize: Int) =
        getResult { service.getDeliveries(offset, pageSize) }
}