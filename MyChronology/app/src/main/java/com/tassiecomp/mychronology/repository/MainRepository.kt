package com.tassiecomp.mychronology.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tassiecomp.mychronology.db.GradeDao
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.HomeGrade


class MainRepository(
    private val gradeDao: GradeDao
) {
    val readAllData: LiveData<List<HomeGrade>> = gradeDao.readAllData()

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