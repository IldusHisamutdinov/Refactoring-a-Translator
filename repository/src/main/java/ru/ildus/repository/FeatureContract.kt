package ru.ildus.repository

import androidx.room.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto
import ru.ildus.repository.room.HistoryEntity

interface FeatureContract {
    interface Interactor<T> {
       suspend fun getData(word: String, fromRemoteSource: Boolean): T
    }

    interface Repository<T> {
       suspend fun getData(word: String): T
    }

    interface RepositoryLocal<T> : Repository<T> {
        suspend fun saveToDB(appState: AppState)
  //      suspend fun dellToDB(appState: AppState)
    }

    interface DataSource<T> {
        suspend fun getData(word: String): T
    }

    interface DataSourceLocal<T> : DataSource<T> {
        suspend fun saveToDB(appState: AppState)
 //       suspend fun dellToDB(appState: AppState)
    }

    interface ApiService {
        @GET("words/search")
        fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<SearchResultDto>>
    }

    @Dao
    interface HistoryDao {

        @androidx.room.Query("SELECT * FROM HistoryEntity")
        suspend fun all(): List<HistoryEntity>

        @androidx.room.Query("SELECT * FROM HistoryEntity WHERE word LIKE :word")
        suspend fun getDataByWord(word: String): HistoryEntity

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(entity: HistoryEntity)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertAll(entities: List<HistoryEntity>)

        @Update
        suspend fun update(entity: HistoryEntity)

        @Delete
        suspend fun delete(entity: HistoryEntity)
    }

}