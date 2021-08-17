package com.tassiecomp.mychronology.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    val readAllData: LiveData<List<HomeGrade>>
    private var repository: MainRepository

    init {
        val gradeDao = HomeGradeDB.getDatabase(application).getGradeDao()
        repository = MainRepository(gradeDao)
        readAllData = repository.readAllData
    }

    fun addSubject(homeGrade: HomeGrade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(homeGrade)
        }

    }
    fun updateSubject(homeGrade: HomeGrade){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(homeGrade)
        }
    }

}