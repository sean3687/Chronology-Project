package com.tassiecomp.mychronology.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.HomeGrade
import java.time.LocalDate

data class Event(val id: String, val text: String, val date: LocalDate)

class DailyRecycleviewAdapter(val onClick: (Event) -> Unit):
    RecyclerView.Adapter<DailyRecycleviewAdapter.DailyRecycleviewViewHolder>(differCallback()) {

    inner class DailyRecycleviewViewHolder(private val binding: DailyRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(events[bindingAdapterPosition])
            }
        }

        fun bind(event: Event) {
            binding.itemEventText.text = event.text
        }
    }
    val events = mutableListOf<Event>()

    class differCallback: DiffUtil.ItemCallback<HomeGrade>(){
        override fun areItemsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeGrade, newItem: HomeGrade): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DailyRecycleviewViewHolder {

        return DailyRecycleviewViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(
                    R.layout.main_home_cardview,
                    viewGroup,
                    false
                )

        )
    }
    override fun onBindViewHolder(viewHolder: DailyRecycleviewViewHolder, position: Int) {
        val grade= getItem(position)
    }




}

class Example3EventsAdapter(val onClick: (Event) -> Unit) :
    RecyclerView.Adapter<Example3EventsAdapter.Example3EventsViewHolder>() {

    val events = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Example3EventsViewHolder {
        return Example3EventsViewHolder(
            Example3EventItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: Example3EventsViewHolder, position: Int) {
        viewHolder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    inner class Example3EventsViewHolder(private val binding: Example3EventItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(events[bindingAdapterPosition])
            }
        }

        fun bind(event: Event) {
            binding.itemEventText.text = event.text
        }
    }
}