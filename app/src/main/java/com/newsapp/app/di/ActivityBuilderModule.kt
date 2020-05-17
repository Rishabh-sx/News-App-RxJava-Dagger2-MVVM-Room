package com.newsapp.app.di


import com.newsapp.app.di.modulesmain.HomeModule
import com.newsapp.app.di.modulesmain.SplashModule
import com.newsapp.app.di.viewmodels.modules.HomeViewModelModule
import com.newsapp.app.di.viewmodels.modules.SplashViewModelsModule
import com.newsapp.app.ui.main.HomeActivity
import com.newsapp.app.ui.newdetails.ArticleDetailActivity
import com.newsapp.app.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [HomeViewModelModule::class, HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [SplashViewModelsModule::class,SplashModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeArticleDetails(): ArticleDetailActivity

}