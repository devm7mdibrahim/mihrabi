package com.devm7mdibrahim.mihrabi.di

import android.content.Context
import com.devm7mdibrahim.mihrabi.model.network.remote.ApiService
import com.devm7mdibrahim.mihrabi.model.network.places.PlacesApiService
import com.devm7mdibrahim.mihrabi.model.network.places.PlacesDataSource
import com.devm7mdibrahim.mihrabi.model.network.places.PlacesDataSourceImpl
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSourceImpl
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.Constants.BASE_URL
import com.devm7mdibrahim.mihrabi.utils.Constants.PLACES_BASE_URL
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("default")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    @Named("places")
    fun providePlacesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(PLACES_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun providePlacesInterceptor(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
                    .addQueryParameter("type", "mosque")
                    .addQueryParameter("key", Constants.GOOGLE_MAPS_KEY)
                    .addQueryParameter("radius", 3000.toString())
                    .build()
            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
            chain.proceed(request)
        }

        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideApiService(@Named("default") retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java)

    @Singleton
    @Provides
    fun providePlacesApiService(@Named("places") retrofit: Retrofit): PlacesApiService = retrofit.create(
        PlacesApiService::class.java)

    @Singleton
    @Provides
    fun providePlacesDataSource(placesApiService: PlacesApiService): PlacesDataSource = PlacesDataSourceImpl(placesApiService)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource = RemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context) = NetworkHelper(context)
}