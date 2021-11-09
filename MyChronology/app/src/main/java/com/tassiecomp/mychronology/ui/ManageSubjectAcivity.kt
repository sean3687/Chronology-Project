package com.tassiecomp.mychronology.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel

class ManageSubjectActivity : AppCompatActivity() {
    lateinit var MainViewModelHomeFragment: MainViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_subject_acivity)

        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)

        //appBar configuration




        //navcontroller
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.ManageSubject_NavHostFragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        findViewById<Toolbar>(R.id.manageSubject_toolbar).setupWithNavController(navController, appBarConfiguration)
//


//        setupActionBarWithNavController(navController, appBarConfiguration)

//        manageSubject_toolbar.setNavigationOnClickListener {
//            finish()
//        }


    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }




}