package com.amrrabbie.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amrrabbie.domain.entity.Post

@Dao
interface PostsDao {

    @Query("delete from posts_table")
    fun deletePosts()

    @Insert(entity = Post::class)
    fun insertPost(post: Post)

    @Query("select * from posts_table")
    fun getAllPosts():LiveData<List<Post>>
}