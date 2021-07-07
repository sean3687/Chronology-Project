package com.tassiecomp.mychronology.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.repository.MainRepository

class MainViewModel(
    val MainRepository: MainRepository
) : ViewModel() {

    val _recyclerViewModel = MutableLiveData<HomeGrade>()

    val recyclerViewModel:LiveData<HomeGrade>
        get() =_recyclerViewModel

}