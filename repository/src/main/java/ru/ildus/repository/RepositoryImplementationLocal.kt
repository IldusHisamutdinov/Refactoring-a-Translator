package ru.ildus.repository

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto

class RepositoryImplementationLocal(private val dataSource: FeatureContract.DataSourceLocal<List<SearchResultDto>>) :
    FeatureContract.RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

//    override suspend fun dellToDB(appState: AppState) {
//        dataSource.dellToDB(appState)
//    }
}