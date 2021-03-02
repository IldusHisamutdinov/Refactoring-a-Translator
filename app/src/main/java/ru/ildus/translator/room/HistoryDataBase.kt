package ru.ildus.translator.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ildus.translator.view.FeatureContract

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): FeatureContract.HistoryDao
}
