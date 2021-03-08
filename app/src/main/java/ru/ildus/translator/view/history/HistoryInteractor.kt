package ru.ildus.translator.view.history

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.repository.FeatureContract

class HistoryInteractor(
    private val repositoryRemote: FeatureContract.Repository<List<DataModel>>,
    private val repositoryLocal: FeatureContract.RepositoryLocal<List<DataModel>>
) : FeatureContract.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }

}