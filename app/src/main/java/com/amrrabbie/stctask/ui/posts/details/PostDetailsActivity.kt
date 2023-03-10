package com.amrrabbie.stctask.ui.posts.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.amrrabbie.domain.entity.Article
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.stctask.R
import com.amrrabbie.stctask.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        bindData(intent)
    }

    private fun bindData(intent: Intent?) {
        intent?.let {
            if (intent.hasExtra("posts_online")) {
                var article: Article? = intent.getParcelableExtra("posts_online")
                article?.let {
                    binding.apply {
                        txttitle.text = article.title

                        img.load(article.urlToImage) {
                            crossfade(true)
                            crossfade(1000)
                        }
                    }
                }
            } else {
                var post: Post? = intent.getParcelableExtra("posts_offline")
                post?.let {
                    binding.apply {
                        txttitle.text = post.title

                        img.load(post.img) {
                            crossfade(true)
                            crossfade(1000)
                        }
                    }
                }

            }
        }
    }
}
