package com.tassiecomp.mychronology.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.tassiecomp.mychronology.models.SubjectItem

class SubjectSelectViewModel(
    application: Application, val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    val mutableSelectedSubject = MutableLiveData<SubjectItem>()
    val selectedSubject: LiveData<SubjectItem> get() = mutableSelectedSubject


    fun setSelectSubject(subjectItem: SubjectItem) {

        mutableSelectedSubject.postValue(subjectItem)
//        repository.readAllData_subject.value?.get(position) ?: SubjectItem(-1, "noSubject", "notAssigned", "0", 0, 0, "#c2c2c2", "${LocalDate.now()}")) as LiveData<List<SubjectItem>>

    }
}