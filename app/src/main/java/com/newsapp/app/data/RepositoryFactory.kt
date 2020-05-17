package com.newsapp.app.data

import androidx.lifecycle.LiveData
import com.newsapp.app.BuildConfig
import com.newsapp.app.constants.AppConstants
import com.newsapp.app.dataclass.Article
import com.newsapp.app.dataclass.HeadlinesResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryFactory @Inject constructor(private var dataManager: DataManager){

    fun getFetchNewsQuery(): Single<HeadlinesResponse> {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap[AppConstants.KEY_COUNTRY] = "us";
        hashMap[AppConstants.KEY_API_KEY] = BuildConfig.API_ID
        return dataManager.makeFetchNewsQuery(hashMap)
    }

    fun insertArticlesInDatabase(articles: List<Article>){
        dataManager.insertArticlesInDatabase(articles)
    }

    fun getArticlesFromDB(): LiveData<List<Article>?> {
        return dataManager.getArticles()
    }
}