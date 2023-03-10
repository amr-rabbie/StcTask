package com.amrrabbie.domain.repo

import androidx.lifecycle.LiveData
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.domain.entity.PostsResponse

interface PostsRepo {

    suspend fun getPosts(q:String,apikey:String,page:Int):PostsResponse

    fun deletePosts()

    fun insertPost(post: Post)

    fun getAllPosts():LiveData<List<Post>>
}