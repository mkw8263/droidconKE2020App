package com.android254.droidconKE2020.network.di

import com.android254.droidconKE2020.network.ApiService
import com.android254.droidconKE2020.network.BuildConfig
import com.android254.droidconKE2020.network.GoogleService
import com.android254.droidconKE2020.network.utils.Constants.BASE_URL
import com.android254.droidconKE2020.network.utils.AuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        BASE_URL.toHttpUrl()
    }

    single {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
        val gsonFactory = GsonConverterFactory.create(gson)
        Retrofit.Builder()
            .baseUrl(get<HttpUrl>())
            .client(get())
            .addConverterFactory(gsonFactory)
            .build()
    }

    single<Interceptor>(named("logger")) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    single {
        OkHttpClient.Builder()
            .addNetworkInterceptor(AuthInterceptor(get()))
            .addInterceptor(get<Interceptor>(named("logger")))
            .build()
    }

    single {
        ApiService(get())
    }

    single {
        GoogleService()
    }
}