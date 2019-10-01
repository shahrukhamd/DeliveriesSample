package com.sasiddiqui.deliveriessample.ui

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setTitle(title: String?) {
    (activity as AppCompatActivity).supportActionBar!!.title = title
}

fun Fragment.setSubTitle(subtitle: String?) {
    (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}