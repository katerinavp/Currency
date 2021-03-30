package com.petukhova.testc.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,

    @ColumnInfo(name = "num_code")
    val numCode: Int,

    @ColumnInfo(name = "char_code")
    val charCode: String,

    @ColumnInfo(name = "nominal")
    val nominal: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "value")
    val value: Double,

    @ColumnInfo(name = "previous")
    val previous: Double
)