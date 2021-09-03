package com.tassiecomp.mychronology

import android.app.Application
import androidx.lifecycle.LiveData
import com.tassiecomp.mychronology.adapters.Event
import com.tassiecomp.mychronology.databinding.FragmentDailyCheckBinding
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.repository.MainRepository
import com.tassiecomp.mychronology.ui.MainViewModel


class App: Application(){
    companion object{
        lateinit var instance: App
        lateinit var viewModel: MainViewModel
        lateinit var readAllData: LiveData<List<HomeGrade>>
        lateinit var repository: MainRepository
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

}