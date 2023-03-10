package com.amrrabbie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amrrabbie.domain.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class PostsDb : RoomDatabase() {

    abstract fun postsdao():PostsDao
}