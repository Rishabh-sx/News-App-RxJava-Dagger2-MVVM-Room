package com.newsapp.app.di

import androidx.lifecycle.ViewModelProvider
import com.newsapp.app.di.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}