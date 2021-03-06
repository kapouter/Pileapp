package com.kapouter.pileapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.model.PlantConverters

@Database(entities = [Plant::class, GrovePlant::class], version = 1)
@TypeConverters(value = [PlantConverters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun groveDao(): GroveDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "pileapp_db"
            ).build()
        }
    }
}