package ru.ildus.repository

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel

class RepositoryImplementationLocal(private val dataSource: FeatureContract.DataSourceLocal<List<DataModel>>) :
    FeatureContract.RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

//    override suspend fun dellToDB(appState: AppState) {
//        dataSource.dellToDB(appState)
//    }
}