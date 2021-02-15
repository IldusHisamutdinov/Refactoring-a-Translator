package ru.ildus.translator.model.datasource

import io.reactivex.Observable
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    FeatureContract.DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
