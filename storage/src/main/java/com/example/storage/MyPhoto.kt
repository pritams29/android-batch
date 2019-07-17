package com.example.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_photo")
data class MyPhoto(
        @PrimaryKey
        @ColumnInfo
        var id : Int = 0,

        @ColumnInfo
        var description : String = "",

        @ColumnInfo
        var title : String = "",

        @ColumnInfo
        var pc : String = "",

        @ColumnInfo
        var path : String = ""
)