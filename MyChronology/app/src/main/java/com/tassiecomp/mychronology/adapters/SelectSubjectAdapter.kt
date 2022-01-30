package com.tassiecomp.mychronology.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments.SelectSubjectPopupDialogDirections
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import kotlinx.android.synthetic.main.subject_selected_cardview.view.*


class SelectSubjectAdapter :
    ListAdapter<SubjectItem, SelectSubjectAdapter.ViewHolder>(differCallback()) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    class differCallback : DiffUtil.ItemCallback<SubjectItem>() {
        override fun areItemsTheSame(oldItem: SubjectItem, newItem: SubjectItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubjectItem, newItem: SubjectItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.subject_selected_cardview,
                    parent,
                    false
                )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectSubject = getItem(position)
        holder.itemView.apply {

            subject_iconLetter.text = selectSubject.title[0].toString()
            subject_CardTitle.text = selectSubject.title
            subject_iconBackground.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor(
                    selectSubject.color
                )
            )
            subject_linearLayout.setOnClickListener {


            }



        }
    }


}