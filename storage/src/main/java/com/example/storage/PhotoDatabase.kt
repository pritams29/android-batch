package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    MyPhoto::class
], version = 1)
abstract  class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao() : MyPhotoDao
}