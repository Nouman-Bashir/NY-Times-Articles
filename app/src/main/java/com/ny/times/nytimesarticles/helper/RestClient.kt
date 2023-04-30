package com.ny.times.nytimesarticles.helper

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ny.times.nytimesarticles.BuildConfig
import com.ny.times.nytimesarticles.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {
    companion object {
        private const val TAG = "RestClient"

        fun <S> createService(serviceClass: Class<S>): S {


            val logging = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val httpClient = OkHttpClient.Builder().apply {
                addNetworkInterceptor(StethoInterceptor())
                addInterceptor(logging)
                connectTimeout(1, TimeUnit.MINUTES)
                readTimeout(1, TimeUnit.MINUTES)
                writeTimeout(1, TimeUnit.MINUTES)
            }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()

            return retrofit.create(serviceClass)
        }
    }
}
