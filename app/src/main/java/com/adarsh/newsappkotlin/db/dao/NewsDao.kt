package com.adarsh.newsappkotlin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adarsh.newsappkotlin.api.GoogleNewsDetail
import com.adarsh.newsappkotlin.api.IndianNewsDetail

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAllGoogleDetails(listOfGoogleNewsDetail: List<GoogleNewsDetail>)

    @Query("Select * from GoogleNewsDetail")
    fun getAllGoogleDetails(): List<GoogleNewsDetail>

    @Query("Delete from GoogleNewsDetail")
    fun clearAllGoogleDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAllIndianNewsDetails(listOfIndianNewsDetail: List<IndianNewsDetail>)

    @Query("Select * from IndianNewsDetail")
    fun getAllIndianNewsDetails(): List<IndianNewsDetail>

    @Query("Delete from IndianNewsDetail")
    fun clearAllIndianDetails()
}