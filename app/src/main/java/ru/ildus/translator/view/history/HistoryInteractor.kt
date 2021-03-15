package ru.ildus.translator.view.history

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto
import ru.ildus.repository.FeatureContract
import ru.ildus.translator.utils.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: FeatureContract.Repository<List<SearchResultDto>>,
    private val repositoryLocal: FeatureContract.RepositoryLocal<List<SearchResultDto>>
) : FeatureContract.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}