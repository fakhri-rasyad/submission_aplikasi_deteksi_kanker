package com.dicoding.asclepius.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
@Database(entities = [Analysis::class], version = 1)
abstract class AnalysisRoomDatabase : RoomDatabase() {
    abstract fun analysisDao(): AnalysisDao
    companion object {
        @Volatile
        private var INSTANCE: AnalysisRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): AnalysisRoomDatabase {
            if (INSTANCE == null) {
                synchronized(AnalysisRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AnalysisRoomDatabase::class.java, "analysis_database")
                        .build()
                }
            }
            return INSTANCE as AnalysisRoomDatabase
        }
    }
}