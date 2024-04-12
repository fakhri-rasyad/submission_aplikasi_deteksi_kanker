package com.dicoding.asclepius.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.Analysis
import java.text.NumberFormat

class HistoryRecyclerViewAdapter(private var historyList : ArrayList<Analysis>) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {
        val analysisHistoryImg : ImageView = item.findViewById(R.id.analysis_history_image)
        val analysisHistoryType : TextView = item.findViewById(R.id.analysis_history_type)
        val analysisHistoryScore : TextView = item.findViewById(R.id.analysis_history_score)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.analysis_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryRecyclerViewAdapter.ViewHolder, position: Int) {
        val analysisHistory = historyList[position]
        holder.analysisHistoryImg.setImageURI(Uri.parse(analysisHistory.uri))
        holder.analysisHistoryType.text = holder.itemView.context.getString(R.string.analysis_type, analysisHistory.type)
        holder.analysisHistoryScore.text = holder.itemView.context.getString(R.string.analysis_score, NumberFormat.getPercentInstance().format(analysisHistory.confidence).toString())
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}