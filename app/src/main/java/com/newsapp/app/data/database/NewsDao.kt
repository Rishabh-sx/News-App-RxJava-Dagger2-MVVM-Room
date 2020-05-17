package com.newsapp.app.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.newsapp.app.dataclass.Article

@Dao
interface NewsDao {

    @Transaction
    fun updateDatabase(articleList: List<Article>?) {
        nukeTable()
        insertArticles(articleList)
    }

    @Query("DELETE FROM article")
    fun nukeTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articleList: List<Article>?)

    @Query("SELECT * FROM article")
    fun fetchNews(): LiveData<List<Article>?>
}