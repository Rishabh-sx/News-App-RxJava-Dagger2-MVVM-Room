package com.newsapp.app.dataclass

data class HeadlinesResponse(var status: String? = null,
                             var totalResults: Int? = null,
                             var articles: List<Article>? = null)