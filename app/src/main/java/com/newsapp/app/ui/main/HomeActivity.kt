package com.newsapp.app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.app.R
import com.newsapp.app.adapters.ArticlesAdapter
import com.newsapp.app.base.BaseActivity
import com.newsapp.app.constants.AppConstants
import com.newsapp.app.dataclass.Article
import com.newsapp.app.dataclass.HeadlinesResponse
import com.newsapp.app.dataclass.NewsResource
import com.newsapp.app.dataclass.Status
import com.newsapp.app.ui.newdetails.ArticleDetailActivity
import com.newsapp.app.util.AppUtils
import com.newsapp.app.util.hide
import com.newsapp.app.util.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), ArticlesAdapter.ArticleListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    private lateinit var mHomeViewModel: HomeViewModel

    override val resourceId: Int
        get() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initObject()
        initObservers()
        setupAdapter()
        setClickListeners()
    }

    private fun setClickListeners() {
        tv_retry.setOnClickListener { fetchNews() }
    }

    private fun setupAdapter() {
        rv.layoutManager = layoutManager
        rv.adapter = articlesAdapter

    }

    private fun initObject() {
        mHomeViewModel = ViewModelProvider(this, providerFactory).get(HomeViewModel::class.java)
        mHomeViewModel.getArticlesFromDB().observe(this,dbObserver)
        mHomeViewModel.headlinesLiveData.observe(this, headlineObserver)

    }

    private val dbObserver = Observer<List<Article>?> {
        if(it!=null && it.isNotEmpty()){
            stopAnimation(animationView)
            articlesAdapter.setAdapterList(it)
        }
    }


    private val headlineObserver = Observer<NewsResource<HeadlinesResponse>> {
        when (it.status) {
            Status.SUCCESS -> {
                stopAnimation(animationView)
            }
            Status.ERROR -> {
                if (articlesAdapter.itemCount == 0) {
                    error_group.show()
                    stopAnimation(animationView)
                }
            }
            Status.LOADING -> {
                if (articlesAdapter.itemCount == 0) {
                    startAnimation(animationView)
                    error_group.hide()
                }
            }

        }

    }


    private fun initObservers() = fetchNews()


    private fun fetchNews() {
        mHomeViewModel.getFetchNewsQuery()
    }


    override fun itemClicked(result: Article) {
        startActivity(Intent(this, ArticleDetailActivity::class.java)
            .apply { putExtra(AppConstants.KEY_ARTICLE, result) })
    }

    override fun share(url: String) {
        AppUtils.shareText(
            url = url,
            activity = this,
            title = resources.getString(R.string.s_share_news)
        )
    }
}
