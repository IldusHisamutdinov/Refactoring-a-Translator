package ru.ildus.translator.view.main

import io.reactivex.Observable
import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract

class MainInteractor(
    private val remoteRepository: FeatureContract.Repository<List<DataModel>>,
    private val localRepository: FeatureContract.Repository<List<DataModel>>
) : FeatureContract.Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
       // if(word.isEmpty()) return  Observable.error(IllegalStateException("слово не может быть пустым"))
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
