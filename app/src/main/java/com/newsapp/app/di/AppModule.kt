package com.newsapp.app.di

import android.app.Application
import android.content.Context
import com.newsapp.app.data.api.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object {

        @Singleton
        @JvmStatic
        @Provides
        fun retrofitClient(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiInterface.ENDPOINT)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        @Singleton
        @JvmStatic
        @Provides
        fun appContext(app:Application): Context{
            return app.applicationContext
        }

    }
}