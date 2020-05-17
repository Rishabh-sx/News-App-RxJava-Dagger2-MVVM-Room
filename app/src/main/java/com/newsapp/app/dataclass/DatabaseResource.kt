package com.newsapp.app.dataclass

class DatabaseResource <ResultType>(var status: Status, var data: ResultType? = null) {

    companion object {

        fun <ResultType> success(data: ResultType): DatabaseResource<ResultType> = DatabaseResource(Status.SUCCESS, data)

        fun <ResultType> loading(): DatabaseResource<ResultType> = DatabaseResource(Status.LOADING)

    }
}