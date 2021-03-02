package ru.ildus.translator.model.repository

import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract

class RepositoryImplementationLocal(private val dataSource: FeatureContract.DataSourceLocal<List<DataModel>>) :
    FeatureContract.RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}