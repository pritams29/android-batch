package com.example.storage

import android.app.Application
import androidx.room.Room

class PhotoApp : Application() {

    val db : PhotoDatabase by lazy {
        Room.databaseBuilder(
                applicationContext,
                PhotoDatabase::class.java, "my-photo-db"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        db
    }
}