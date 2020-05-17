package com.newsapp.app.data

import androidx.lifecycle.LiveData
import com.newsapp.app.data.api.ApiManager
import com.newsapp.app.data.database.AppDatabase
import com.newsapp.app.dataclass.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(var apiManager: ApiManager, var appDatabase: AppDatabase) {

    fun makeFetchNewsQuery(map: HashMap<String, String>) = apiManager.makeFetchNewsQuery(map)

    fun insertArticlesInDatabase(articles: List<Article>){
        appDatabase.newsDao().updateDatabase(articles)
    }

    fun getArticles(): LiveData<List<Article>?> {
       return appDatabase.newsDao().fetchNews()
    }


}
