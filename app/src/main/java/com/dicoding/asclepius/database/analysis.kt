package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Analysis(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "imageUri")
    var uri: String? = null,

    @ColumnInfo(name  = "type")
    var type: String? = null,

    @ColumnInfo(name = "confidence")
    var confidence: Float = 0.0F
) : Parcelable
