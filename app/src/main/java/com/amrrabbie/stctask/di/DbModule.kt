package com.amrrabbie.stctask.di

import android.app.Application
import androidx.room.Room
import com.amrrabbie.data.db.PostsDao
import com.amrrabbie.data.db.PostsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providePostsDb(application: Application):PostsDb=
        Room.databaseBuilder(application.applicationContext,
        PostsDb::class.java,"posts_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun providePostsDao(postsDb: PostsDb):PostsDao=
        postsDb.postsdao()


}