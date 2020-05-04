package com.animescrap.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.animescrap.data.model.catalog.entity.CatalogResponse
import com.animescrap.data.model.history.entity.HistoryResponse
import com.animescrap.data.source.local.dao.CatalogDao
import com.animescrap.data.source.local.dao.HistoryDao


@Database(entities = [CatalogResponse::class, HistoryResponse::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun catalogDao(): CatalogDao
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE anime ADD title_encoded VARCHAR")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "anime_scrap_database")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}