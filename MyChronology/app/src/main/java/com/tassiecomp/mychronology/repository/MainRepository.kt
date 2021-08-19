package com.tassiecomp.mychronology.repository

import androidx.lifecycle.LiveData
import com.tassiecomp.mychronology.db.GradeDao
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.HomeGrade


class MainRepository(
    private val gradeDao: GradeDao
) {
    val readAllData_subject: LiveData<List<HomeGrade>> = gradeDao.readAllData_subject()
    val readAllData_daily: LiveData<List<DailyGrade>> = gradeDao.readAllData_daily()

    suspend fun addSubject(homeGrade: HomeGrade) {
        gradeDao.addSubject(homeGrade)

    }
    suspend fun updateSubject(homeGrade: HomeGrade){
        gradeDao.updateSubject(homeGrade)
    }
    suspend fun deleteSubject(homeGrade: HomeGrade){
        gradeDao.deleteSubject(homeGrade)
    }



}