package com.tassiecomp.mychronology

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.repository.MainRepository
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel


class App: Application(){
    companion object{
        lateinit var instance: App
        lateinit var viewModel: MainViewModel
        lateinit var readAllData: LiveData<List<SubjectItem>>
        lateinit var repository: MainRepository
        lateinit var mContext: Context
    }



}