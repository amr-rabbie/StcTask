package com.amrrabbie.stctask.ui.posts.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androiddeveloper.amrrabbie.kotlinapidb.utils.Network
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrrabbie.domain.entity.Article
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.stctask.databinding.ActivityPostsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityPostsListBinding
    lateinit var postsAdapter: PostsAdapter
    lateinit var postsAdapterOffline: PostsAdapterOffline

    val viewmodel:PostsViewModel by viewModels()


    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityPostsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Network.isNetworkAvailable(this)) {
            setupRecyclerView()
            loadData()
        }else{
            loadDataFromDb()
        }
    }



    private fun setupRecyclerView() {

        postsAdapter = PostsAdapter(this)

        binding.postsrecycler.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(
                this@PostsListActivity
            )
            setHasFixedSize(true)
        }

    }

    @InternalCoroutinesApi
    private fun loadData() {

        postsAdapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.pbar.isVisible = true
            else {
                binding.pbar.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG)
                        .show()
                }

            }
        }


        lifecycleScope.launch {

            viewmodel.postslist.collect {
                it?.let {

                    deletePosts()

                    Log.d("aaa", "load: ${it.toString()}")
                    postsAdapter.submitData(it)
                    postsAdapter.notifyDataSetChanged()

                    val list = postsAdapter.snapshot().items
                    insertPosts(list)
                }

            }

        }
    }

    private fun insertPosts(it: List<Article>) {
        for(item in it){
            var post=Post(item.title,item.urlToImage)
            viewmodel.insertPost(post)
        }
    }

    private fun deletePosts() {
        viewmodel.deletePosts()
    }

    private fun loadDataFromDb() {
       viewmodel.getAllPosts()

        viewmodel.posts.observe(this, Observer { posts ->
            if(!posts.isNullOrEmpty()){
                postsAdapterOffline= PostsAdapterOffline(this,posts)

                binding.postsrecycler.apply {
                    adapter=postsAdapterOffline
                    hasFixedSize()
                    layoutManager=LinearLayoutManager(this@PostsListActivity)
                }
                binding.pbar.visibility=View.GONE
                Toast.makeText(this,"This is cashed data!",Toast.LENGTH_LONG).show()
            }else{
                binding.pbar.visibility=View.GONE
                Toast.makeText(this,"No data cashed yet!",Toast.LENGTH_LONG).show()
            }
        })
    }
}