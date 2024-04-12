package com.dicoding.asclepius.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.database.Analysis
import com.dicoding.asclepius.repository.AnalysisRepository

class HistoryViewModel(application: Application) : ViewModel() {
    private val mAnalysisRepository : AnalysisRepository = AnalysisRepository(application)
    fun getAll() : LiveData<List<Analysis>> = mAnalysisRepository.getAllNotes()
}