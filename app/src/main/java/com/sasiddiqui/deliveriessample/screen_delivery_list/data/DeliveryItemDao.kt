package com.sasiddiqui.deliveriessample.screen_delivery_list.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem

/**
 * The Data Access Object for the DeliveryItem class.
 */
@Dao
interface DeliveryItemDao {

    @Query("SELECT * FROM delivery_item ORDER BY id DESC")
    fun getDeliveryItem(): DataSource.Factory<Int, DeliveryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<DeliveryItem>)
}