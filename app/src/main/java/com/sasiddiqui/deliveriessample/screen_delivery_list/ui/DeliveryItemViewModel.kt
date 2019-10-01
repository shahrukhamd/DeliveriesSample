package com.sasiddiqui.deliveriessample.screen_delivery_list.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sasiddiqui.deliveriessample.di.CoroutineScropeIO
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.DeliveryItemRepository
import com.sasiddiqui.deliveriessample.util.ConnectivityUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DeliveryItemViewModel @Inject constructor(context : Context, repository: DeliveryItemRepository,
                                                @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {
    
    var connectivityAvailable: Boolean = ConnectivityUtil.isConnected(context)

    val deliveryItems by lazy {
        repository.observePagedSets(
            connectivityAvailable, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
