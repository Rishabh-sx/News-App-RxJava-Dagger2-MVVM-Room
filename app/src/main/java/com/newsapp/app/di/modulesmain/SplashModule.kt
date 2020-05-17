package com.newsapp.app.di.modulesmain

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashModule {

    @Provides
    fun compositeDisposable() = CompositeDisposable()

}