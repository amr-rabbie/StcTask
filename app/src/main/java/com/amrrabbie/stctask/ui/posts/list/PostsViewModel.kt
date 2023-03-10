package com.amrrabbie.stctask.ui.posts.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.domain.usecase.PostsUseCase
import com.amrrabbie.stctask.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel
@Inject
constructor(private val postsUseCase: PostsUseCase) : ViewModel(){

    lateinit var posts:LiveData<List<Post>>

    val postslist= Pager(PagingConfig(pageSize = 10)){
        PostsPagingSource(postsUseCase, Constants.Api_Key,"tesla")
    }.flow.cachedIn(viewModelScope)

    fun deletePosts()=
        postsUseCase.deletePosts()

    fun insertPost(post: Post)=
        postsUseCase.insertPost(post)

    fun getAllPosts(){
        posts=postsUseCase.getAllPosts()
    }

}