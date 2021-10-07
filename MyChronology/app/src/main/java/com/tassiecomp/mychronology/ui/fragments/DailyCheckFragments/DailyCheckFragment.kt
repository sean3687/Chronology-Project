package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.DailyRecycleviewAdapter
import com.tassiecomp.mychronology.adapters.Event
import com.tassiecomp.mychronology.databinding.CalendarDayLayoutBinding
import com.tassiecomp.mychronology.databinding.CalendarHeaderLayoutBinding
import com.tassiecomp.mychronology.databinding.FragmentDailyCheckBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class DailyCheckFragment : Fragment(R.layout.fragment_daily_check) {

    var selectedDate: LocalDate? = null
    val today = LocalDate.now()
    val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    val selectionFormatter = DateTimeFormatter.ofPattern("yyyy MMM d")
    private val events = mutableMapOf<LocalDate, List<Event>>()



    lateinit var binding: FragmentDailyCheckBinding

    private val eventsAdapter = DailyRecycleviewAdapter {
        AlertDialog.Builder(requireContext())
            .setMessage("delete?")
            .setPositiveButton("delete") { _, _ ->
                deleteEvent(it)
            }
            .setNegativeButton("close", null)
            .show()
    }


    private fun deleteEvent(event: Event) {
        Log.d("tagg", "message")
        //delete  event from database
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // make changes to view and its children here
        val view = inflater.inflate(R.layout.fragment_daily_check, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentDailyCheckBinding.bind(view)
        binding.dailyCheckRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = eventsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL))
        }
        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        val currentDate = LocalDate.now()
        Log.d("TAGG", "current month: $currentMonth")

        //range of calendar
        binding.calendarView.apply {
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)
            scrollToDate(today)
        }


        if (savedInstanceState == null) {
            binding.calendarView.post {
                // Show today's events initially.
                selectDate(today)
            }
        }

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayLayoutBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }
                }
            }
        }

        //when day clicked
        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.binding.DayText
                val dotView = container.binding.DotView

                textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.makeVisible()
                    when (day.date) {
                        today -> {
                            textView.setTextColorRes(R.color.white)
                            textView.setBackgroundResource(R.drawable.calendar_today_bg)
                            dotView.makeInVisible()
                        }
                        selectedDate -> {
                            textView.setTextColorRes(R.color.lightBlue)
                            textView.setBackgroundResource(R.drawable.calendar_selected_bg)
                            dotView.makeInVisible()
                        }
                        else -> {
                            textView.setTextColorRes(R.color.black)
                            textView.background = null
                            dotView.isVisible = events[day.date].orEmpty().isNotEmpty()
                        }
                    }
                } else {
                    textView.makeInVisible()
                    dotView.makeInVisible()
                }

            }


        }

        binding.calendarView.monthScrollListener = {
            binding.YearText.text = it.yearMonth.year.toString()
            binding.MonthText.text = titleSameYearFormatter.format(it.yearMonth)
                if (it.year == today.year) {
                titleSameYearFormatter.format(it.yearMonth)
            } else {
                titleFormatter.format(it.yearMonth)
            }

            // Select the first day of the month when
            // we scroll to a new month.
            selectDate(it.yearMonth.atDay(1))
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = CalendarHeaderLayoutBinding.bind(view).legendLayout.root
        }

        binding.calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }

                }
            }
        }
    }

    private fun selectDate(date: LocalDate) {

        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.calendarView.notifyDateChanged(it) }
            binding.calendarView.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun updateAdapterForDate(date: LocalDate) {
        eventsAdapter.apply { //viewmodel로 observe되면 실행되게

        }


    }




}

