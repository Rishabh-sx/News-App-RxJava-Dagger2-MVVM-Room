package com.newsapp.app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.newsapp.app.R
import com.newsapp.app.base.BaseActivity
import com.newsapp.app.ui.main.HomeActivity
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    @Inject
    lateinit var disposables : CompositeDisposable

    private lateinit var mSplashViewModel: SplashViewModel

    override val resourceId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObjects()
    }

    private fun initObjects() {
        startAnimation(animationView)
        mSplashViewModel = ViewModelProvider(this,providerFactory).get(SplashViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        startSplashTimer()
    }

    private fun startSplashTimer() {
        mSplashViewModel.timeObservable.subscribe(object : Observer<Long?>{
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }
            override fun onNext(t: Long) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                startHome()
            }
        })
    }

    private fun startHome() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java)
                .apply{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK})
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}
