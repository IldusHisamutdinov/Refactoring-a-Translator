package ru.ildus.translator.view.main

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto
import ru.ildus.repository.FeatureContract
import ru.ildus.translator.utils.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: FeatureContract.Repository<List<SearchResultDto>>,
    private val repositoryLocal: FeatureContract.RepositoryLocal<List<SearchResultDto>>
) : FeatureContract.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
            if (fromRemoteSource) {
                appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
                repositoryLocal.saveToDB(appState)
  //              repositoryLocal.dellToDB(null)
            } else {
                appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
            }
        return appState
    }

}

