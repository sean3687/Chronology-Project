package com.tassiecomp.mychronology.db

import androidx.room.*
import com.tassiecomp.mychronology.models.HomeGrade


@Dao
interface GradeDao {
    @Query("SELECT * FROM grades")
    fun getAll():List<HomeGrade>

    @Insert
    fun insert():List<HomeGrade>

    @Update
    fun update():List<HomeGrade>

    @Delete
    fun delete():List<HomeGrade>

}