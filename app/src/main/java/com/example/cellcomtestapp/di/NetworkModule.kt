package com.example.cellcomtestapp.di

import com.example.data.BuildConfig
import com.example.data.source.remote.movies.api.MovieApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun clientInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addNetworkInterceptor(clientInterceptor())
                    .build()
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
}