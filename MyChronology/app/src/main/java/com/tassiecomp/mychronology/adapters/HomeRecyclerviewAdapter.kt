package com.tassiecomp.mychronology.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.HomeGrade
import kotlinx.android.synthetic.main.main_home_cardview.view.*

class HomeRecyclerviewAdapter(): RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val differCallback = object: DiffUtil.ItemCallback<HomeGrade>(){
        override fun areItemsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this,differCallback)

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
        val todayGrade= differ.currentList[position]
        viewHolder.itemView.apply{
            CardTitle.text = todayGrade.title
            Weighting.text = todayGrade.weigthing
            CurrentScore.text = todayGrade.description
        }
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}