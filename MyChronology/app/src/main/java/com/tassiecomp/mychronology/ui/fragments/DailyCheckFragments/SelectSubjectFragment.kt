package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.DailyCheckTodoAdapter
import com.tassiecomp.mychronology.adapters.HomeRecyclerviewAdapter
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_manage_subject.*


class SelectSubjectFragment : Fragment() {

    private lateinit var MainViewModelHomeFragment: MainViewModel
    lateinit var DailyCheckTodoAdapter: DailyCheckTodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_subject, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        MainViewModelHomeFragment.readAllData_subject.observe(viewLifecycleOwner,{
            //객체를 생성만하고 recyclerView.adapter 에 할당하는 코드가 없다는 뜻이에여. 옵저버 내부의 코드를 (recyclerView.adapter as HomeRecyclerviewAdapter).submitList(it) 으로 고치면 될 것 같네여
            (recyclerView.adapter as HomeRecyclerviewAdapter).submitList(it)
            Log.d("TAGG", "obsereved")
        })
    }

    private fun setupRecyclerView() {
        DailyCheckTodoAdapter = DailyCheckTodoAdapter()
        recyclerView.apply {
            adapter = DailyCheckTodoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }
}