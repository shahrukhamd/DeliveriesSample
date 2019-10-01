package com.sasiddiqui.deliveriessample.repository

import androidx.paging.PageKeyedDataSource
import com.sasiddiqui.deliveriessample.repository.data.DataResult
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.DeliveryItemDao
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DeliveryItemPagedDataSource @Inject constructor(
    private val dataSource: DeliveryItemRemoteDataSource,
    private val dao: DeliveryItemDao,
    private val scope: CoroutineScope): PageKeyedDataSource<Int, DeliveryItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DeliveryItem>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 20)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DeliveryItem>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 20)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DeliveryItem>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 20)
        }
    }

    private fun fetchData(offset: Int, pageSize: Int, callback: (List<DeliveryItem>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchData(offset, pageSize)
            if (response.status == DataResult.Status.SUCCESS) {
                dao.insertAll(response.data!!)
                callback(response.data)
            } else if (response.status == DataResult.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
    }
}