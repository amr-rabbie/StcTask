package com.amrrabbie.stctask.ui.posts.list

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.amrrabbie.domain.entity.Post
import com.amrrabbie.stctask.R
import com.amrrabbie.stctask.ui.posts.details.PostDetailsActivity
import org.junit.*


internal class PostsListActivityTest {

    @Rule
    var mActivityTestRule: ActivityScenarioRule<PostsListActivity> = ActivityScenarioRule<PostsListActivity>(
        PostsListActivity::class.java
    )

    private val mActivity: PostsListActivity? = null

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun testLaunch() {
        val v: View = mActivity!!.findViewById(R.id.postsrecycler)
        Assert.assertNotNull(v)
    }


}