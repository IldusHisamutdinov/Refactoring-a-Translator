package ru.ildus.translator.di

import androidx.room.Room
import org.koin.dsl.module
import ru.ildus.repository.RepositoryImplementation
import ru.ildus.repository.RepositoryImplementationLocal
import ru.ildus.model.data.DataModel
import ru.ildus.repository.datasource.RetrofitImplementation
import ru.ildus.repository.datasource.RoomDataBaseImplementation
import ru.ildus.repository.room.HistoryDataBase
import ru.ildus.repository.FeatureContract
import ru.ildus.translator.view.history.HistoryInteractor
import ru.ildus.translator.view.history.HistoryViewModel
import ru.ildus.translator.view.main.MainInteractor
import ru.ildus.translator.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<FeatureContract.Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<FeatureContract.RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}