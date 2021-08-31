package com.tassiecomp.mychronology.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.DailyGrade
import java.time.LocalDate

data class Event(val id: String, val text: String, val date: LocalDate)


class DailyRecycleviewAdapter(val onClick: (Event) -> Unit) :
    ListAdapter<DailyGrade, DailyRecycleviewAdapter.DailyRecycleviewViewHolder>(differCallback()) {

    inner class DailyRecycleviewViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class differCallback : DiffUtil.ItemCallback<DailyGrade>() {
        override fun areItemsTheSame(oldItem: DailyGrade, newItem: DailyGrade): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: DailyGrade, newItem: DailyGrade): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): DailyRecycleviewViewHolder {
        return DailyRecycleviewViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.calendar_subject_cardview, viewGroup, false)
        )

    }


    override fun onBindViewHolder(
        viewHolder: DailyRecycleviewViewHolder,
        position: Int
    ) {
        val DailyGrade = getItem(position)
        viewHolder.itemView.apply {



        }
    }
}



