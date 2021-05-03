package com.lupesoft.appshoppingcenter.infrastructure.api

import com.lupesoft.appshoppingcenter.BuildConfig.BaseUrl
import com.lupesoft.appshoppingcenter.infrastructure.api.daos.MovieDaoRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    fun create(): MovieDaoRetrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

        return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDaoRetrofit::class.java)
    }
}
