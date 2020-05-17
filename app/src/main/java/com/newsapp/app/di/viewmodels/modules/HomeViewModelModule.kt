package com.newsapp.app.di.viewmodels.modules

import androidx.lifecycle.ViewModel
import com.newsapp.app.di.ViewModelKey
import com.newsapp.app.ui.main.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel?): ViewModel?
}