package com.newsapp.app.data.api

import com.newsapp.app.dataclass.HeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("top-headlines")
    fun makeFetchNewsQuery(@QueryMap queryMap: HashMap<String,String>): Single<HeadlinesResponse>

    companion object {
        const val ENDPOINT = "https://newsapi.org/v2/"
    }
}
