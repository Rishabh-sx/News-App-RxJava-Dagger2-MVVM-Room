package com.newsapp.app.data.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(var apiClient: ApiInterface) {

    fun makeFetchNewsQuery(map: HashMap<String, String>) = apiClient.makeFetchNewsQuery(map)
}
