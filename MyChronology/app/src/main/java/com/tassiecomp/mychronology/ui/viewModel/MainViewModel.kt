package com.tassiecomp.mychronology.ui.viewModel

import android.app.Application
import android.text.TextUtils
import android.util.Log

import androidx.lifecycle.*
import androidx.room.ColumnInfo
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainViewModel(
    application: Application , val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    val readAllData_subject: LiveData<List<SubjectItem>>
    val readAllData_daily: LiveData<List<DailyGrade>>
    val getSubjectTitleList: LiveData<List<String>>
    val getSubjectColorList: LiveData<List<String>>
    private var repository: MainRepository

    init {
        val gradeDao = HomeGradeDB.getDatabase(application).getGradeDao()
        repository = MainRepository(gradeDao)
        readAllData_subject = repository.readAllData_subject
        readAllData_daily = repository.readAllData_daily
        getSubjectTitleList = repository.getSubjectTitleList
        getSubjectColorList = repository.getSubjectColorList

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