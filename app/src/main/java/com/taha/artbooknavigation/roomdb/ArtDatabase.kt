package com.taha.artbooknavigation.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taha.artbooknavigation.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}