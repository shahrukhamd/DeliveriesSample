package com.sasiddiqui.deliveriessample.screen_delivery_list.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sasiddiqui.deliveriessample.repository.DeliveryItemPagedDataSourceFactory
import com.sasiddiqui.deliveriessample.repository.DeliveryItemRemoteDataSource
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class DeliveryItemRepository @Inject constructor(private val dao: DeliveryItemDao,
                                                 private val remoteSource: DeliveryItemRemoteDataSource
) {

    fun observePagedSets(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable) observeRemotePagedSets(coroutineScope)
        else observeLocalPagedSets()

    private fun observeLocalPagedSets(): LiveData<PagedList<DeliveryItem>> {
        val dataSourceFactory = dao.getDeliveryItem()
        return LivePagedListBuilder(dataSourceFactory,
            DeliveryItemPagedDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<DeliveryItem>> {
        val dataSourceFactory = DeliveryItemPagedDataSourceFactory(remoteSource, dao, ioCoroutineScope)
        return LivePagedListBuilder(dataSourceFactory,
            DeliveryItemPagedDataSourceFactory.pagedListConfig()).build()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: DeliveryItemRepository? = null

        fun getInstance(dao: DeliveryItemDao, deliveryItemRemoteDataSource: DeliveryItemRemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: DeliveryItemRepository(dao, deliveryItemRemoteDataSource).also { instance = it }
            }
    }
}