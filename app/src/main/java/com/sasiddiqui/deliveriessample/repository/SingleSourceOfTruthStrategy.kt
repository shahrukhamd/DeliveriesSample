package com.sasiddiqui.deliveriessample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.sasiddiqui.deliveriessample.repository.data.DataResult
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [DataResult.Status.SUCCESS] - with data from database
 * [DataResult.Status.ERROR] - if error has occurred from any source
 * [DataResult.Status.LOADING]
 */
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> DataResult<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<DataResult<T>> =
        liveData(Dispatchers.IO) {
            emit(DataResult.loading<T>())
            val source = databaseQuery.invoke().map {
                DataResult.success(
                    it
                )
            }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == DataResult.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == DataResult.Status.ERROR) {
                emit(
                    DataResult.error<T>(
                        responseStatus.message!!
                    )
                )
                emitSource(source)
            }
        }