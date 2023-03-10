package com.amrrabbie.stctask.ui.posts.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amrrabbie.domain.entity.Article
import com.amrrabbie.stctask.databinding.PostRowBinding
import com.amrrabbie.stctask.ui.posts.details.PostDetailsActivity

class PostsAdapter  (var context: Context) : PagingDataAdapter<Article, PostsAdapter.PostsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostsViewHolder {
        return PostsViewHolder(PostRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostsViewHolder, position: Int) {
        var item: Article? =getItem(position)
        holder.binding.apply {
            if (item != null) {
                txttitle.text=item.title
            }

        }

        holder.itemView.setOnClickListener {mview->
            if (item != null) {
                var intent=Intent(context,PostDetailsActivity::class.java)
                intent.putExtra("posts_online",item)
                context.startActivity(intent)
            }
        }


    }



    inner class  PostsViewHolder(val binding: PostRowBinding):
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}