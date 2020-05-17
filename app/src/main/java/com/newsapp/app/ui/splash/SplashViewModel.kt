package com.newsapp.app.ui.splash

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SplashViewModel @Inject constructor() : ViewModel() {

    val timeObservable: Observable<Long>
            get() = Observable
        .timer(3, java.util.concurrent.TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
