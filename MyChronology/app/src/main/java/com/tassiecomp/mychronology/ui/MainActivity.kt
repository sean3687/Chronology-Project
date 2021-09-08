package com.tassiecomp.mychronology.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var MainViewModelHomeFragment:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)

        bottom_navigation.setupWithNavController(nav_host_fragment.findNavController())
        supportFragmentManager.commit {
            addToBackStack("")
        }
        val transaction: FragmentTransaction =
            getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.main_conta iner, movieInfoFragment)
        // 해당 transaction 을 Back Stack 에 저장
        transaction.addToBackStack(null);
        // transaction 실행
        transaction.commit();


    }





}


