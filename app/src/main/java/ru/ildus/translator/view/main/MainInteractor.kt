package ru.ildus.translator.view.main

import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract


class MainInteractor(
    private val repositoryRemote: FeatureContract.Repository<List<DataModel>>,
    private val repositoryLocal: FeatureContract.Repository<List<DataModel>>
) : FeatureContract.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        // if(word.isEmpty()) return  Observable.error(IllegalStateException("слово не может быть пустым"))
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}

