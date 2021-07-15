package com.tassiecomp.mychronology

import android.app.Application
import com.tassiecomp.mychronology.ui.MainViewModel

class App: Application(){
    companion object{
        lateinit var instance: App
        lateinit var viewModel: MainViewModel
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

}