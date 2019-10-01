package com.sasiddiqui.deliveriessample.repository.data

/**
 * A generic class that holds a value with its loading status.
 *
 * DataResult is usually created by the Repository classes where they return
 * `LiveData<DataResult<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class DataResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): DataResult<T> {
            return DataResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): DataResult<T> {
            return DataResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): DataResult<T> {
            return DataResult(Status.LOADING, data, null)
        }
    }
}