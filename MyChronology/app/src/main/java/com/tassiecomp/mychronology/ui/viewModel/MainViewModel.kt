package com.tassiecomp.mychronology.ui.viewModel

import android.app.Application
import android.text.TextUtils

import androidx.lifecycle.*
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainViewModel(
    application: Application , val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    val readAllData_subject: LiveData<List<SubjectItem>>
    val readAllData_daily: LiveData<List<DailyGrade>>

    private var repository: MainRepository

    init {
        val gradeDao = HomeGradeDB.getDatabase(application).getGradeDao()
        repository = MainRepository(gradeDao)
        readAllData_subject = repository.readAllData_subject
        readAllData_daily = repository.readAllData_daily


    }

    fun selectedDateData(createdDate:String): LiveData<List<DailyGrade>>{
        return repository.searchSelected(createdDate)
    }


    fun addSubject(subjectItem: SubjectItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(subjectItem)
        }

    }
    fun updateSubject(subjectItem: SubjectItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(subjectItem)
        }
    }
    fun deleteSubject(subjectItem: SubjectItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubject(subjectItem)
        }
    }

    fun addDailyCheck(dailyItem: DailyGrade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDailyCheck(dailyItem)
        }
    }

}