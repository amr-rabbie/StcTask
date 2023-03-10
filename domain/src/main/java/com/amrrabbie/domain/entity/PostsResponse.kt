package com.amrrabbie.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):Parcelable