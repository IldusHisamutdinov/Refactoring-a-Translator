package ru.ildus.translator.view.main

import io.reactivex.Observable
import ru.ildus.translator.di.NAME_LOCAL
import ru.ildus.translator.di.NAME_REMOTE
import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: FeatureContract.Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: FeatureContract.Repository<List<DataModel>>
) : FeatureContract.Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
       // if(word.isEmpty()) return  Observable.error(IllegalStateException("слово не может быть пустым"))
        return if (fromRemoteSource) {
            repositoryRemote 
        } else {
            repositoryLocal
			}.getData(word).map { AppState.Success(it) }
    }
}
