package ru.ildus.translator.model.datasource

import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    FeatureContract.DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
