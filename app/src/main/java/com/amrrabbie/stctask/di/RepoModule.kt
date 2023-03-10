package com.amrrabbie.stctask.di

import com.amrrabbie.data.db.PostsDao
import com.amrrabbie.data.remote.PostsApiService
import com.amrrabbie.data.repo.PostsRepoImpl
import com.amrrabbie.domain.repo.PostsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providePostsRepo(postsApiService: PostsApiService,postsDao: PostsDao):PostsRepo=
        PostsRepoImpl(postsApiService,postsDao)
}