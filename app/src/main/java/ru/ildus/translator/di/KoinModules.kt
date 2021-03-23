package ru.ildus.translator.di

import androidx.room.Room
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.ildus.repository.RepositoryImplementation
import ru.ildus.repository.RepositoryImplementationLocal
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto
import ru.ildus.repository.datasource.RetrofitImplementation
import ru.ildus.repository.datasource.RoomDataBaseImplementation
import ru.ildus.repository.room.HistoryDataBase
import ru.ildus.repository.FeatureContract
import ru.ildus.translator.view.history.HistoryActivity
import ru.ildus.translator.view.history.HistoryInteractor
import ru.ildus.translator.view.history.HistoryViewModel
import ru.ildus.translator.view.main.MainActivity
import ru.ildus.translator.view.main.MainInteractor
import ru.ildus.translator.view.main.MainViewModel

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen, historyScreen ))
}

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<FeatureContract.Repository<List<SearchResultDto>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<FeatureContract.RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { MainInteractor(get(), get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        viewModel { HistoryViewModel(get()) }
        scoped { HistoryInteractor(get(), get()) }
    }
}