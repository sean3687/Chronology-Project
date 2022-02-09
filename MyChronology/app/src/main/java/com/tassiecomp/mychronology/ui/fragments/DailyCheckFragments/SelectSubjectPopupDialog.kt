package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.SelectSubjectAdapter
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import com.tassiecomp.mychronology.ui.viewModel.SubjectSelectViewModel
import kotlinx.android.synthetic.main.select_subject_dialog_fragment.*


class SelectSubjectPopupDialog: BottomSheetDialogFragment() {

    lateinit var MainViewModelHomeFragment: MainViewModel
    lateinit var subjectSelectViewModel: SubjectSelectViewModel
    lateinit var selectSubjectAdapter: SelectSubjectAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.select_subject_dialog_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)
        subjectSelectViewModel = ViewModelProvider(requireActivity()).get(SubjectSelectViewModel::class.java)

        MainViewModelHomeFragment.readAllData_subject.observe(viewLifecycleOwner, Observer {
            //객체를 생성만하고 recyclerView.adapter 에 할당하는 코드가 없다는 뜻이에여. 옵저버 내부의 코드를 (recyclerView.adapter as HomeRecyclerviewAdapter).submitList(it) 으로 고치면 될 것 같네여
            (selectSubject_recyclerView.adapter as SelectSubjectAdapter).submitList(it)
            Log.d("TAGG", "obsereved")
        })

        selectSubjectAdapter.setOnItemClickListener( object : SelectSubjectAdapter.OnItemClickListener{
            override fun onItemClick(subjectItem: SubjectItem) {
                Log.d("subjectItem", "$subjectItem")
                subjectSelectViewModel.setSelectSubject(subjectItem)
                dismiss()
            }

        })

    }

    private fun setupRecyclerView() {
        selectSubjectAdapter = SelectSubjectAdapter(subjectItem = null)
        selectSubject_recyclerView.apply {
            adapter = selectSubjectAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }




}