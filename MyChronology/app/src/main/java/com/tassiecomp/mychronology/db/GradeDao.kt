package com.tassiecomp.mychronology.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.tassiecomp.mychronology.models.HomeGrade


@Dao
interface GradeDao {
    @Query("SELECT * FROM homeGrades")
    fun readAllData(): LiveData<List<HomeGrade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(homeGrade:HomeGrade)

    @Update
    suspend fun updateSubject(homeGrade: HomeGrade)

}