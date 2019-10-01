package com.sasiddiqui.deliveriessample.screen_delivery_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sasiddiqui.deliveriessample.R
import com.sasiddiqui.deliveriessample.databinding.FragmentItemDetailsBinding
import com.sasiddiqui.deliveriessample.di.Injectable
import com.sasiddiqui.deliveriessample.ui.setTitle

class ItemDetailsFragment: Fragment(), Injectable, OnMapReadyCallback {

    private val args: ItemDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentItemDetailsBinding>(
            inflater, R.layout.fragment_item_details, container, false).apply {
            lifecycleOwner = this@ItemDetailsFragment
        }

        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        args.itemLocation?.let {
            setTitle(it.address)
        }

        setHasOptionsMenu(false)
        return binding?.root
    }

    override fun onMapReady(map: GoogleMap?) {
        map ?: return

        with(map) {
            args.itemLocation?.let {
                if (it.lat != null && it.lng != null) {
                    val location = LatLng(it.lat, it.lng)
                    addMarker(MarkerOptions().position(location))
                    animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
                }
            }
        }
    }
}