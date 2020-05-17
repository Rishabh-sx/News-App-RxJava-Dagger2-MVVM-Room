package com.newsapp.app.di.modulesmain

import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.app.adapters.ArticlesAdapter
import com.newsapp.app.dataclass.Article
import com.newsapp.app.ui.main.HomeActivity
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class HomeModule {

    @Provides
    fun articlesAdapter(listener: HomeActivity?, list: ArrayList<Article>): ArticlesAdapter {
        return ArticlesAdapter(listener!!,list)
    }

    @Provides
    fun layoutManager(context: HomeActivity?): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun getMutableArticleList() = ArrayList<Article>()

}