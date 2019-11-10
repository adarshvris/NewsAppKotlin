package com.adarsh.newsappkotlin.api

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GoogleNewsResponse(
    @Expose @SerializedName("status") val status: String?,
    @Expose @SerializedName("totalResults") val totalResults: String?,
    @Expose @SerializedName("articles") val listOfGoogleNews: List<GoogleNewsDetail>?
) : Serializable

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)

@Entity
data class GoogleNewsDetail(
    @Expose @Embedded @SerializedName("source") val googleSource: Source?,
    @Expose @SerializedName("author") val author: String?,
    @Expose @SerializedName("title") val title: String?,
    @Expose @SerializedName("description") val description: String?,
    @Expose @SerializedName("url") val contentUrl: String?,
    @Expose @SerializedName("urlToImage") val imageUrl: String?,
    @Expose @SerializedName("publishedAt") val publishedAt: String?,
    @PrimaryKey @Expose @SerializedName("content") val content: String
) : Serializable

data class IndianNewsResponse(
    @Expose @SerializedName("status") val status: String?,
    @Expose @SerializedName("totalResults") val totalResults: String?,
    @Expose @SerializedName("articles") val listOfIndianNewsDetail: List<IndianNewsDetail>?
) : Serializable

@Entity
data class IndianNewsDetail(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    @Expose @Embedded @SerializedName("source") val googleSource: Source?,
    @Expose @SerializedName("author") val author: String?,
    @Expose @SerializedName("title") val title: String?,
    @Expose @SerializedName("description") val description: String?,
    @Expose @SerializedName("url") val contentUrl: String,
    @Expose @SerializedName("urlToImage") val imageUrl: String?,
    @Expose @SerializedName("publishedAt") val publishedAt: String?,
    @Expose @SerializedName("content") val content: String?
) : Serializable