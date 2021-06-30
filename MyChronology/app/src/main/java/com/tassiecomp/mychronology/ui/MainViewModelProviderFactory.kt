package com.tassiecomp.mychronology.ui

class MainViewModelProviderFactory(
    val newsRespository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass:Class<T>): T{
        return MainViewModel
    }
}