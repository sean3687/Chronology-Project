package com.tassiecomp.mychronology.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.HomeGrade


@Dao
interface GradeDao {
    @Query("SELECT * FROM homeGrades")
    fun readAllData_subject(): LiveData<List<HomeGrade>>

    @Query("SELECT * FROM dailyGrades")
    fun readAllData_daily(): LiveData<List<DailyGrade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(homeGrade:HomeGrade)

    @Update
    suspend fun updateSubject(homeGrade: HomeGrade)

    @Delete
    suspend fun deleteSubject(homeGrade: HomeGrade)

}