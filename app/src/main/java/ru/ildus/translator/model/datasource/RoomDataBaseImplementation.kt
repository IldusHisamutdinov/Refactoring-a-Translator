package ru.ildus.translator.model.datasource

import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.utils.convertDataModelSuccessToEntity
import ru.ildus.translator.utils.mapHistoryEntityToSearchResult
import ru.ildus.translator.view.FeatureContract

class RoomDataBaseImplementation(private val historyDao: FeatureContract.HistoryDao) : FeatureContract.DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
