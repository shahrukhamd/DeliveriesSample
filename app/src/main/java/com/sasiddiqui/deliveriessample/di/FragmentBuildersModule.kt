package com.sasiddiqui.deliveriessample.di

import com.sasiddiqui.deliveriessample.screen_delivery_list.ui.DeliveryItemListFragment
import com.sasiddiqui.deliveriessample.screen_delivery_list.ui.ItemDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDeliveryListFragment(): DeliveryItemListFragment

    @ContributesAndroidInjector
    abstract fun contributeItemDetailsFragment(): ItemDetailsFragment
}
