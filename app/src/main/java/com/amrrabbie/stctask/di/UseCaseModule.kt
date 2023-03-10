package com.amrrabbie.stctask.di

import com.amrrabbie.domain.repo.PostsRepo
import com.amrrabbie.domain.usecase.PostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providePostsUseCase(postsRepo: PostsRepo):PostsUseCase=
        PostsUseCase(postsRepo)
}