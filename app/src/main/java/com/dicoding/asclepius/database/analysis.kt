package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Analysis(
    @PrimaryKey
    @ColumnInfo(name = "imageUri")
    var uri: String,

    @ColumnInfo(name  = "type")
    var type: String? = null,

    @ColumnInfo(name = "confidence")
    var confidence: Float = 0.0F
) : Parcelable
