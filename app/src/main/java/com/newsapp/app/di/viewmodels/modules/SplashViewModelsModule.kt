package com.newsapp.app.di.viewmodels.modules

import androidx.lifecycle.ViewModel
import com.newsapp.app.di.ViewModelKey
import com.newsapp.app.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindAuthViewModel(viewModel: SplashViewModel?): ViewModel?
}