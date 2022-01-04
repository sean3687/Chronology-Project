package com.tassiecomp.mychronology.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem



@Dao
interface GradeDao {
    @Query("SELECT * FROM homeGrades")
    fun readAllData_subject(): LiveData<List<SubjectItem>>

    @Query("SELECT * FROM dailyGrades")
    fun readAllData_daily(): LiveData<List<DailyGrade>>

    @Query("SELECT dailyGrades.* FROM dailyGrades Join dailyFts ON(dailyGrades.createdDate = dailyFts.createdDate) WHERE dailyFts MATCH :query")
    fun searchSelectedDateData(query:String? ): LiveData<List<DailyGrade>>

    @Query("SELECT subject_title FROM homeGrades ")
    fun getSubjectTitleList(): LiveData<List<String>>

    @Query("SELECT subject_color FROM homeGrades")
    fun getSubjectColorList(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subjectItem:SubjectItem)

    @Update
    suspend fun updateSubject(subjectItem: SubjectItem)

    @Delete
    suspend fun deleteSubject(subjectItem: SubjectItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDailyCheck(dailyItem:DailyGrade)

    @Update
    suspend fun updateDailyCheck(dailyItem: DailyGrade)

    @Delete
    suspend fun deleteDailyCheck(dailyItem: DailyGrade)


}