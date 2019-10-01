package com.sasiddiqui.deliveriessample.di

import android.app.Application
import com.sasiddiqui.deliveriessample.api.DeliveryService
import com.sasiddiqui.deliveriessample.repository.data.AppDatabase
import com.sasiddiqui.deliveriessample.repository.DeliveryItemRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDeliveryService(@DeliveryAPI okhttpClient: OkHttpClient,
                               converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, DeliveryService::class.java)

    @Singleton
    @Provides
    fun provideDeliveryItemRemoteDataSource(deliveryService: DeliveryService)
            =
        DeliveryItemRemoteDataSource(deliveryService)

    @DeliveryAPI
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideDeliveryItemDao(db: AppDatabase) = db.deliveryItemDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(DeliveryService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
