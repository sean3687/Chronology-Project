package com.tassiecomp.mychronology.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.DailyGrade
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.fragments.ManageSubjectFragment.ManangeSubjectFragmentDirections
import kotlinx.android.synthetic.main.main_home_cardview.view.*
import kotlinx.android.synthetic.main.main_subjectitemlist_child_item.view.*

class DailyCheckTodoAdapter: ListAdapter<DailyGrade, DailyCheckTodoAdapter.ViewHolder>(differCallback()) {
    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view)

    class differCallback: DiffUtil.ItemCallback<DailyGrade>(){
        override fun areItemsTheSame(oldItem: DailyGrade, newItem: DailyGrade): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DailyGrade, newItem: DailyGrade): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(
                    R.layout.main_subjectitemlist_child_item,
                    viewGroup,
                    false
                )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.itemView.apply {
            item_checkbox.isChecked = todo.checkBox
            item_text.text = todo.textData
        }
    }
}