package com.example.storage

import androidx.room.*

@Dao
interface MyPhotoDao {

    @Insert
    fun create(photo: MyPhoto): Unit

    @Update
    fun update(photo: MyPhoto): Unit

    @Delete
    fun delete(photo: MyPhoto): Unit

    @Query("select * from my_photo where id = :id")
    fun selectPhoto(id: Int): MyPhoto

    @Query("select * from my_photo")
    fun allPhotos() : List<MyPhoto>
}