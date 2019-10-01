package com.sasiddiqui.deliveriessample.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.DeliveryItemDao
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DeliveryItemPagedDataSourceFactory @Inject constructor(
    private val dataSource: DeliveryItemRemoteDataSource,
    private val dao: DeliveryItemDao,
    private val scope: CoroutineScope) : DataSource.Factory<Int, DeliveryItem>() {

    private val liveData = MutableLiveData<DeliveryItemPagedDataSource>()

    override fun create(): DataSource<Int, DeliveryItem> {
        val source = DeliveryItemPagedDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}