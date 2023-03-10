package com.amrrabbie.stctask.ui.posts.details

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.*
import com.amrrabbie.stctask.R


internal class PostDetailsActivityTest {

    @Rule
    var mActivityTestRule: ActivityScenarioRule<PostDetailsActivity> = ActivityScenarioRule<PostDetailsActivity>(
        PostDetailsActivity::class.java
    )

    private val mActivity: PostDetailsActivity? = null

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun testLaunch() {
        val v: View = mActivity!!.findViewById(R.id.txttitle)
        Assert.assertNotNull(v)
    }

    @Test
    fun testLaunch2() {
        val v: View = mActivity!!.findViewById(R.id.img)
        Assert.assertNotNull(v)
    }
}