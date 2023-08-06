package com.reza.app.di

import com.reza.start.data.api.StartApiService
import com.reza.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val THIRTY_SECONDS = 30L
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(THIRTY_SECONDS, TimeUnit.SECONDS)
            .readTimeout(THIRTY_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(THIRTY_SECONDS, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
        // TODO: adding api key here
//            .addInterceptor { chain ->
//                val newRequest =
//                    chain.request().newBuilder().addHeader("x-api-key", BuildConfig.API_KEY).build()
//                chain.proceed(newRequest)
//            }
        return if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor).build()
        } else {
            builder.build()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        // TODO base url must be placed here
        //.baseUrl(BuildConfig.BASE_URL_NEWS)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideStartApiService(retrofit: Retrofit): StartApiService =
        retrofit.create(StartApiService::class.java)
}