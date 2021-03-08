package ru.ildus.repository

import ru.ildus.model.data.DataModel

class RepositoryImplementation(private val dataSource: FeatureContract.DataSource<List<DataModel>>) :
    FeatureContract.Repository<List<DataModel>> {
    override suspend fun getData(word: String):List<DataModel> {
        return dataSource.getData(word)
    }
}
