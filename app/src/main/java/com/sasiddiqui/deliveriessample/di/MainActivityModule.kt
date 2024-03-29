package com.sasiddiqui.deliveriessample.di

import com.sasiddiqui.deliveriessample.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}