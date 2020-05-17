package com.newsapp.app.dataclass

data class NewsResource<ResultType>(var status: Status, var data: ResultType? = null, var throwable: Throwable?=null) {

    companion object {

        fun <ResultType> success(data: ResultType): NewsResource<ResultType> = NewsResource(Status.SUCCESS, data)

        fun <ResultType> error(throwable: Throwable): NewsResource<ResultType> = NewsResource(Status.ERROR,throwable = throwable)

        fun <ResultType> loading(): NewsResource<ResultType> = NewsResource(Status.LOADING)

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;
}