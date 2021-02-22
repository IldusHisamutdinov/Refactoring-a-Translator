package ru.ildus.translator.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.model.datasource.RetrofitImplementation
import ru.ildus.translator.model.datasource.RoomDataBaseImplementation
import ru.ildus.translator.model.repository.RepositoryImplementation
import ru.ildus.translator.view.FeatureContract
import ru.ildus.translator.view.main.MainInteractor
import ru.ildus.translator.view.main.MainViewModel

val application = module {
    single<FeatureContract.Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<FeatureContract.Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}