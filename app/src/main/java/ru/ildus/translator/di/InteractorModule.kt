package ru.ildus.translator.di

import dagger.Module
import dagger.Provides
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import ru.ildus.translator.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: FeatureContract.Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: FeatureContract.Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
