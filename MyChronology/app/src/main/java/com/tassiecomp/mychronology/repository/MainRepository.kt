package com.tassiecomp.mychronology.repository

import androidx.lifecycle.LiveData
import com.tassiecomp.mychronology.db.GradeDao
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.HomeGrade
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainRepository(
    private val gradeDao: GradeDao
) {
    val readAllData_subject: LiveData<List<HomeGrade>> = gradeDao.readAllData_subject()
    val readAllData_daily: LiveData<List<DailyGrade>> = gradeDao.readAllData_daily()

    suspend fun addSubject(homeGrade: HomeGrade) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.addSubject(homeGrade)
        }


    }
    suspend fun updateSubject(homeGrade: HomeGrade){
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.updateSubject(homeGrade)
        }
    }
    suspend fun deleteSubject(homeGrade: HomeGrade){
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.deleteSubject(homeGrade)
        }
    }



}