package com.sasiddiqui.deliveriessample.screen_delivery_list.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("lng")
    val lng: Double? = null,

    @field:SerializedName("lat")
    val lat: Double? = null
): Parcelable