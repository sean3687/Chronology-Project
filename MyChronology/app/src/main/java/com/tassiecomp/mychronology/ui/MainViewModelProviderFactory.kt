package com.tassiecomp.mychronology.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tassiecomp.mychronology.repository.MainRepository

class MainViewModelProviderFactory(
    val MainRepository: MainRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository) as T
    }

}
   
