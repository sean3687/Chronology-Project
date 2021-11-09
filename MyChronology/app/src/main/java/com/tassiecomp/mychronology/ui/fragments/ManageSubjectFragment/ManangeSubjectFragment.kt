package com.tassiecomp.mychronology.ui.fragments.ManageSubjectFragment


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.HomeRecyclerviewAdapter
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import com.tassiecomp.mychronology.ui.ManageSubjectActivity
import kotlinx.android.synthetic.main.fragment_manage_subject.*
import kotlinx.android.synthetic.main.fragment_manage_subject.view.*


class ManangeSubjectFragment : Fragment() {
    private lateinit var MainViewModelHomeFragment: MainViewModel
    lateinit var homeRecyclerviewAdapter: HomeRecyclerviewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_manage_subject, container, false)

        view.apply {
            floatingActionButton.setOnClickListener {

                findNavController().navigate(R.id.action_manageSubjectFragment_to_createSubjectFragment)

            }
            backSpace_manage.setOnClickListener{
                activity?.finish()
            }
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainViewModelHomeFragment = (activity as ManageSubjectActivity).MainViewModelHomeFragment
        Log.d("TAGG", "RecyclerView created")

        setupRecyclerView()

        MainViewModelHomeFragment.readAllData_subject.observe(viewLifecycleOwner, Observer {
            //객체를 생성만하고 recyclerView.adapter 에 할당하는 코드가 없다는 뜻이에여. 옵저버 내부의 코드를 (recyclerView.adapter as HomeRecyclerviewAdapter).submitList(it) 으로 고치면 될 것 같네여
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












