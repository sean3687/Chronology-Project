package com.tassiecomp.mychronology.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    abstract val readAllData: LiveData<List<HomeGrade>>
    abstract val repository: MainRepository

    init {
        val gradeDao = HomeGradeDB.getDatabase(application).getGradeDao()
    }

    fun addSubject(homeGrade: HomeGrade){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(homeGrade)
        }

    }

}