package com.tassiecomp.mychronology.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.fragments.ManageSubjectFragment.ManangeSubjectFragmentDirections
import kotlinx.android.synthetic.main.main_home_cardview.view.*

    class HomeRecyclerviewAdapter: ListAdapter<SubjectItem, HomeRecyclerviewAdapter.ViewHolder>(differCallback()) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    class differCallback:DiffUtil.ItemCallback<SubjectItem>(){
        override fun areItemsTheSame(oldItem: SubjectItem, newItem: SubjectItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubjectItem, newItem: SubjectItem): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.main_home_cardview,
                viewGroup,
                false
            )

        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todayGrade= getItem(position)
        viewHolder.itemView.apply{
            CardTitle.text = todayGrade.title
            Weighting.text = todayGrade.weigthing
            Description.text = todayGrade.description
            subject_iconLetter.text = todayGrade.title[0].toString()
            subject_iconBackground.backgroundTintList = ColorStateList.valueOf(Color.parseColor(
                todayGrade.color
            ))

            subject_iconLetter.setOnClickListener{
                val action = ManangeSubjectFragmentDirections.actionManageSubjectFragmentToUpdateFragment(todayGrade)
                findNavController().navigate(action)
            }
            item_subject.setOnClickListener{
                val action = ManangeSubjectFragmentDirections.actionManageSubjectFragmentToUpdateFragment(todayGrade)
                findNavController().navigate(action)
            }


        }
    }


}