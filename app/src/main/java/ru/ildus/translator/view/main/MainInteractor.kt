package ru.ildus.translator.view.main

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.repository.FeatureContract

class MainInteractor(
    private val repositoryRemote: FeatureContract.Repository<List<DataModel>>,
    private val repositoryLocal: FeatureContract.RepositoryLocal<List<DataModel>>
) : FeatureContract.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
            if (fromRemoteSource) {
                appState = AppState.Success(repositoryRemote.getData(word))
                repositoryLocal.saveToDB(appState)
  //              repositoryLocal.dellToDB(null)
            } else {
                appState = AppState.Success(repositoryLocal.getData(word))
            }
        return appState
    }

}

