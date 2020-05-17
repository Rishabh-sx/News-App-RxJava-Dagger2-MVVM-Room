package com.newsapp.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.newsapp.app.data.RepositoryFactory
import com.newsapp.app.dataclass.Article
import com.newsapp.app.dataclass.DatabaseResource
import com.newsapp.app.dataclass.HeadlinesResponse
import com.newsapp.app.dataclass.NewsResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private var repositoryFactory: RepositoryFactory) : ViewModel() {

    var headlinesLiveData = MutableLiveData<NewsResource<HeadlinesResponse>>()

    fun getArticlesFromDB(): LiveData<List<Article>?> {
        return repositoryFactory.getArticlesFromDB()
    }

    private var compositeDisposable = CompositeDisposable()

    fun getFetchNewsQuery() {
        headlinesLiveData.value = NewsResource.loading()

        val singleObservable = repositoryFactory.getFetchNewsQuery()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<HeadlinesResponse>() {
                override fun onSuccess(t: HeadlinesResponse) {
                    if (t.status.equals("ok",true) && t.articles?.size!!>0)
                        repositoryFactory.insertArticlesInDatabase(t.articles!!)
                }
                override fun onError(e: Throwable) {
                    headlinesLiveData.postValue(NewsResource.error(e))
                }

            })

        compositeDisposable.add(singleObservable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}