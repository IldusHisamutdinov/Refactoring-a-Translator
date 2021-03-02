package ru.ildus.translator.model.repository

import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract

class RepositoryImplementation(private val dataSource: FeatureContract.DataSource<List<DataModel>>) :
    FeatureContract.Repository<List<DataModel>> {
    override suspend fun getData(word: String):List<DataModel> {
        return dataSource.getData(word)
    }
}
