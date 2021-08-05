package com.tassiecomp.mychronology.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.HomeRecyclerviewAdapter
import com.tassiecomp.mychronology.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var MainViewModelHomeFragment: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home,container, false)

        val adapter = HomeRecyclerviewAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)
        MainViewModelHomeFragment.readAllData.observe(viewLifecycleOwner, Observer{ subject->


        })

        view.create_new_subject.setOnClickListener {
            findNavController()
                .navigate(R.id.action_homeFragment_to_createSubjectFragment)

            Log.d("TAGG", "Create subject clicked")
        }

        return view
    }



    }











