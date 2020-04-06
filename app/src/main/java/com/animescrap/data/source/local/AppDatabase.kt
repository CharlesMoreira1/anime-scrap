package com.animescrap.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.animescrap.data.model.catalog.entity.CatalogResponse
import com.animescrap.data.source.local.dao.CatalogDao

@Database(entities = [CatalogResponse::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun catalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

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
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}