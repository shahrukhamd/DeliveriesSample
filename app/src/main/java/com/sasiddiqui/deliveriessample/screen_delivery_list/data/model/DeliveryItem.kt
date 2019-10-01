package com.sasiddiqui.deliveriessample.screen_delivery_list.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "delivery_item")
data class DeliveryItem (
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("imageUrl")
    val imageUrl: String?,
    @field:SerializedName("description")
    val description: String?,
    @Embedded
    @field:SerializedName("location")
    val location: Location? = null
)