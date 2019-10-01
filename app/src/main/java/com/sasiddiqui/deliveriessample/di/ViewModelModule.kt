package com.sasiddiqui.deliveriessample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sasiddiqui.deliveriessample.screen_delivery_list.ui.DeliveryItemViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DeliveryItemViewModel::class)
    abstract fun bindDeliveryListViewModel(viewModel: DeliveryItemViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}