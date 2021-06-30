package com.tassiecomp.mychronology.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tassiecomp.mychronology.models.HomeGrade

@Dao
interface GradeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(homeGrade: HomeGrade):Long

    @Query("SELECT * FROM ")
}