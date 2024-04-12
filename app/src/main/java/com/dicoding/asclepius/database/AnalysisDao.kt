package com.dicoding.asclepius.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnalysisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(analysis: Analysis)

    @Query("SELECT * FROM analysis ORDER BY imageUri ASC")
    fun getAll() : LiveData<List<Analysis>>
}