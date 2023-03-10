package com.amrrabbie.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "posts_table")
@Parcelize
class Post(var title:String,var img:String) : Parcelable{

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}