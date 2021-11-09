package com.tassiecomp.mychronology.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments.DailyCheckFragment
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    lateinit var MainViewModelHomeFragment: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    lateinit var toggle: ActionBarDrawerToggle
    val currentDate = LocalDate.now()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        CalendarProgress()
        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)

        //Load DailyCheck Fragment
        GlobalScope.launch() {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, DailyCheckFragment())
                .commit()

        }

        //top app bar
        topAppBar.setNavigationOnClickListener {
            //open navigation drawer
            drawerLayout.openDrawer(GravityCompat.START)

        }

        //navigation drawer

        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.bringToFront()


        val newIntent = Intent(this@MainActivity, ManageSubjectActivity::class.java)
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.drawer_manageSubject -> {
                    startActivity(newIntent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

            }
            true
        }


    }

    fun CalendarProgress() {
        progress_circular.visibility = View.VISIBLE
        progress_background.visibility = View.VISIBLE
        Handler().postDelayed({
            progress_circular.visibility = View.INVISIBLE
            progress_background.visibility = View.INVISIBLE
        }, 3000)

    }
}











