package com.newsapp.app.di

import android.app.Application
import com.newsapp.app.BaseApplication
import com.newsapp.app.data.database.AppDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    RoomModule::class,
    AppModule::class,
    ViewModelFactoryModule::class

])
interface AppComponent : AndroidInjector<BaseApplication?> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder?
        fun build(): AppComponent?
    }
}