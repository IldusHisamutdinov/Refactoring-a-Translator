package ru.ildus.translator.view

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ildus.translator.model.data.DataModel

interface FeatureContract {
    interface Interactor<T> {
       suspend fun getData(word: String, fromRemoteSource: Boolean): T
    }

    interface Repository<T> {
       suspend fun getData(word: String): T
    }

    interface DataSource<T> {
        suspend fun getData(word: String): T
    }

    interface ApiService {
        @GET("words/search")
        fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
    }

}