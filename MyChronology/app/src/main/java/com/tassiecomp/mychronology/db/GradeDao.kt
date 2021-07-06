package com.tassiecomp.mychronology.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tassiecomp.mychronology.models.HomeGrade


@Dao
interface GradeDao {
    @Query("SELECT * FROM grades")
    fun getAll(): List<HomeGrade>

    @Insert
    fun insert(): List<HomeGrade>

}