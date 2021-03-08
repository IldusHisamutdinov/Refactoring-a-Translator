package ru.ildus.repository.datasource

import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.repository.FeatureContract
import ru.ildus.repository.convertDataModelSuccessToEntity
import ru.ildus.repository.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: FeatureContract.HistoryDao) :
    FeatureContract.DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }


//    override suspend fun dellToDB(appState: AppState) {
//        convertDataModelSuccessToEntity(appState)?.let {
//            historyDao.delete(it)
//        }
//    }
}
