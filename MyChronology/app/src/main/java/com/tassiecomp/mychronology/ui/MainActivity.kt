package com.tassiecomp.mychronology.ui


import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.DailyCheckTodoAdapter
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments.DailyCheckFragment
import com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments.SelectSubjectPopupDialog
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import com.tassiecomp.mychronology.ui.viewModel.SubjectSelectViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    lateinit var MainViewModelHomeFragment: MainViewModel
    lateinit var subjectSelectViewModel: SubjectSelectViewModel
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var DailyCheckTodoAdapter: DailyCheckTodoAdapter
    val currentDate = LocalDate.now()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        CalendarProgress()
        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)
        subjectSelectViewModel = ViewModelProvider(this).get(SubjectSelectViewModel::class.java)

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

        //DailyCheck Fragment
        supportFragmentManager.beginTransaction().add(R.id.calendarViewFrameLayout, DailyCheckFragment()).commit()

        selectSubject_text.setOnClickListener {

            val dialog = SelectSubjectPopupDialog()
            val ft = supportFragmentManager.beginTransaction()
            dialog.show(ft, "DialogFragment")

        }
        val stockString = "NoSubject"
        val stockColor = "#c2c2c2"
        selectSubject_text.text = stockString[0].toString()
        selectSubject_background.backgroundTintList = ColorStateList.valueOf(
            Color.parseColor(
                stockColor
        ))

        subjectSelectViewModel.selectedSubject.observe(this, Observer{
            Log.d("Lifecycle", "Lifecycler called")
        })


//            if(item != null){
//                val default = SubjectItem(-1, "noSubject", "notAssigned", "0", 0, 0, "#c2c2c2", "${LocalDate.now()}")
//                setSelectedSubject(default)
//            }
//
//            setSelectedSubject(item)

        setupTodoRecyclerView()

        addTodoList.setOnClickListener {

        }


    }

//    fun CalendarProgress() {
//        progress_circular.visibility = View.VISIBLE
//        progress_background.visibility = View.VISIBLE
//        Handler().postDelayed({
//            progress_circular.visibility = View.INVISIBLE
//            progress_background.visibility = View.INVISIBLE
//        }, 3000)
//
//    }

    private fun setSelectedSubject(subjectItem: SubjectItem){
        selectSubject_text.text = subjectItem.title[0].toString()
        selectSubject_background.backgroundTintList = ColorStateList.valueOf(
            Color.parseColor(
                subjectItem.color
            )
        )
    }
    fun setupTodoRecyclerView() {
        DailyCheckTodoAdapter = DailyCheckTodoAdapter()
        daily_check_recyclerView.apply {
            adapter = DailyCheckTodoAdapter
            layoutManager = LinearLayoutManager(context)

        }

    }
}











