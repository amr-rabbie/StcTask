package com.amrrabbie.stctask.ui.posts.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.stctask.databinding.PostRowBinding
import com.amrrabbie.stctask.ui.posts.details.PostDetailsActivity

class PostsAdapterOffline (val context: Context, val list:List<Post>) : RecyclerView.Adapter<PostsAdapterOffline.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapterOffline.PostsViewHolder {
        return PostsViewHolder(PostRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PostsAdapterOffline.PostsViewHolder, position: Int) {
        var post: Post =list.get(position)

        holder.binding.apply {

            post?.let {
               txttitle.text=post.title
            }

        }

        holder.itemView.setOnClickListener {mview->
            if (post != null) {
                var intent= Intent(context, PostDetailsActivity::class.java)
                intent.putExtra("posts_offline",post)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount()=
        list.size

    inner class PostsViewHolder(val binding: PostRowBinding):
        RecyclerView.ViewHolder(binding.root)
}