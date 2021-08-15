package com.tassiecomp.mychronology.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.HomeGrade
import kotlinx.android.synthetic.main.main_home_cardview.view.*

class HomeRecyclerviewAdapter: ListAdapter<HomeGrade, HomeRecyclerviewAdapter.ViewHolder>(differCallback()) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    class differCallback:DiffUtil.ItemCallback<HomeGrade>(){
        override fun areItemsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
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

        }
    }


}