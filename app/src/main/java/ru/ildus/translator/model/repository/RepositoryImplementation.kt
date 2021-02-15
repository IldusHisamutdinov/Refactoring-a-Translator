package ru.ildus.translator.model.repository

import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: FeatureContract.DataSource<List<DataModel>>) :
    FeatureContract.Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
