package com.dicoding.asclepius.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.Analysis
import com.dicoding.asclepius.database.AnalysisDao
import com.dicoding.asclepius.database.AnalysisRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AnalysisRepository(application: Application) {
    private val mAnalysisDao: AnalysisDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = AnalysisRoomDatabase.getDatabase(application)
        mAnalysisDao = db.analysisDao()
    }
    fun getAllNotes(): LiveData<List<Analysis>> = mAnalysisDao.getAll()
    fun insert(analysis: Analysis) {
        executorService.execute { mAnalysisDao.insert(analysis) }
    }
}