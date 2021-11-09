package com.tassiecomp.mychronology.repository

import androidx.lifecycle.LiveData
import com.tassiecomp.mychronology.db.GradeDao
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainRepository(
    private val gradeDao: GradeDao
) {
    val readAllData_subject: LiveData<List<SubjectItem>> = gradeDao.readAllData_subject()
    val readAllData_daily: LiveData<List<DailyGrade>> = gradeDao.readAllData_daily()

    fun searchSelected(selectedDate:String?): LiveData<List<DailyGrade>>{
        return gradeDao.searchSelectedDateData(selectedDate)
    }

    suspend fun addSubject(subjectItem: SubjectItem) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.addSubject(subjectItem)
        }

    }

    suspend fun updateSubject(subjectItem: SubjectItem) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.updateSubject(subjectItem)
        }
    }

    suspend fun deleteSubject(subjectItem: SubjectItem) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.deleteSubject(subjectItem)
        }
    }

    suspend fun addDailyCheck(dailyItem: DailyGrade) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.addDailyCheck(dailyItem)
        }
    }

    suspend fun updateDailyCheck(dailyItem: DailyGrade) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.updateDailyCheck(dailyItem)
        }
    }

    suspend fun deleteDailyCheck(dailyItem: DailyGrade) {
        CoroutineScope(Dispatchers.IO).launch {
            gradeDao.deleteDailyCheck(dailyItem)
        }
    }




}