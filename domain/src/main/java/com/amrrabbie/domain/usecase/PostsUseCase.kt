package com.amrrabbie.domain.usecase

import com.amrrabbie.domain.entity.Post
import com.amrrabbie.domain.repo.PostsRepo

class PostsUseCase(private val postsRepo: PostsRepo) {

    suspend fun getPosts(q:String,apikey:String,page:Int)=
        postsRepo.getPosts(q,apikey,page)

    fun deletePosts()=
        postsRepo.deletePosts()

    fun insertPost(post: Post)=
        postsRepo.insertPost(post)

    fun getAllPosts()=
        postsRepo.getAllPosts()
}