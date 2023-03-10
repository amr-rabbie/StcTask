package com.amrrabbie.data.repo

import androidx.lifecycle.LiveData
import com.amrrabbie.data.db.PostsDao
import com.amrrabbie.data.remote.PostsApiService
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.domain.entity.PostsResponse
import com.amrrabbie.domain.repo.PostsRepo

class PostsRepoImpl(private val postsApiService: PostsApiService,private val postsDao: PostsDao) : PostsRepo {

    override suspend fun getPosts(q: String, apikey: String, page: Int): PostsResponse =
        postsApiService.getPosts(q,apikey,page)

    override fun deletePosts() =
        postsDao.deletePosts()

    override fun insertPost(post: Post) =
        postsDao.insertPost(post)

    override fun getAllPosts(): LiveData<List<Post>> {
        return postsDao.getAllPosts()
    }

}