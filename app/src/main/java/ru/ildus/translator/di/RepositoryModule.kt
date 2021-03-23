package ru.ildus.translator.di

import dagger.Module
import dagger.Provides
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.model.datasource.RetrofitImplementation
import ru.ildus.translator.model.datasource.RoomDataBaseImplementation
import ru.ildus.translator.model.repository.RepositoryImplementation
import ru.ildus.translator.view.FeatureContract
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: FeatureContract.DataSource<List<DataModel>>): FeatureContract.Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: FeatureContract.DataSource<List<DataModel>>): FeatureContract.Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): FeatureContract.DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): FeatureContract.DataSource<List<DataModel>> = RoomDataBaseImplementation()
}
