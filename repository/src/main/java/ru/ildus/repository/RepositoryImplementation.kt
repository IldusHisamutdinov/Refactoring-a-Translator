package ru.ildus.repository

import ru.ildus.model.data.dto.SearchResultDto

class RepositoryImplementation(private val dataSource: FeatureContract.DataSource<List<SearchResultDto>>) :
    FeatureContract.Repository<List<SearchResultDto>> {
    override suspend fun getData(word: String):List<SearchResultDto> {
        return dataSource.getData(word)
    }
}
