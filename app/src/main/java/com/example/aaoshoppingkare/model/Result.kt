package com.example.aaoshoppingkare.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Result(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val quoteId: Int,
    @ColumnInfo val _id: String,
    @ColumnInfo val author: String,
    @ColumnInfo val content: String,

    )