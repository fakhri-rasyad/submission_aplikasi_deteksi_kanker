package com.dicoding.asclepius.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.database.Analysis
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.view.adapter.HistoryRecyclerViewAdapter
import com.dicoding.asclepius.viewmodel.HistoryViewModel
import com.dicoding.asclepius.viewmodel.ViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        historyViewModel = obtainViewModel(this@HistoryActivity)

        historyViewModel.getAll().observe(this){
            showRecyclerView(it as ArrayList<Analysis>)
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(HistoryViewModel::class.java)
    }

    private fun showRecyclerView(analysisHistoryList : ArrayList<Analysis>){
        val layoutManager = LinearLayoutManager(this)
        binding.historyRv.layoutManager = layoutManager
        binding.historyRv.setHasFixedSize(true)
        val adapter = HistoryRecyclerViewAdapter(analysisHistoryList)
        binding.historyRv.adapter = adapter
    }
}