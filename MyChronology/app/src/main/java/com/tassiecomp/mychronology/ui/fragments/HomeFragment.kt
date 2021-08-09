package com.tassiecomp.mychronology.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.HomeRecyclerviewAdapter
import com.tassiecomp.mychronology.ui.MainActivity
import com.tassiecomp.mychronology.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var MainViewModelHomeFragment: MainViewModel
    lateinit var homeRecyclerviewAdapter: HomeRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.create_new_subject.setOnClickListener {
            findNavController()
                .navigate(R.id.action_homeFragment_to_createSubjectFragment)

            Log.d("TAGG", "Create subject clicked")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainViewModelHomeFragment = (activity as MainActivity).MainViewModelHomeFragment
        Log.d("TAGG", "RecyclerView created")
        
        setupRecyclerView()

        MainViewModelHomeFragment.readAllData.observe(viewLifecycleOwner, Observer {
            (recyclerView.adapter as HomeRecyclerviewAdapter).submitList(it)
            Log.d("TAGG", "obsereved")
        })
    }

    private fun setupRecyclerView() {
        homeRecyclerviewAdapter = HomeRecyclerviewAdapter()
        recyclerView.apply {
            adapter = homeRecyclerviewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }


}












