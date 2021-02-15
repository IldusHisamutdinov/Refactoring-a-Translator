package ru.ildus.translator.view

import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.http.GET
import retrofit2.http.Query

interface FeatureContract {
    interface View {
        fun renderData(appState: AppState)
    }

    interface ISchedulerProvider {
        fun ui(): Scheduler
        fun io(): Scheduler
    }

    interface Interactor<T> {
        fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
    }

    interface Repository<T> {
        fun getData(word: String): Observable<T>
    }

    interface DataSource<T> {
        fun getData(word: String): Observable<T>
    }

    interface Presenter<T : AppState, V : FeatureContract.View> {
        fun attachView(view: V)
        fun detachView(view: V)
        fun getData(word: String, isOnline: Boolean)
    }

    interface ApiService {
        @GET("words/search")
        fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
    }
}