package com.amrrabbie.stctask.ui.posts.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amrrabbie.domain.entity.Article
import com.amrrabbie.domain.usecase.PostsUseCase

class PostsPagingSource  (private val postsUseCase: PostsUseCase, private val key:String,private val q:String) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: 1
            val response = postsUseCase.getPosts(q,key,currentPage)
            val responseData = mutableListOf<Article>()
            val data = response.articles?: emptyList()
            //Thread.sleep(1000)
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}