package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*


class DailyCheckFragment : Fragment(R.layout.fragment_daily_check) {

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
        TODO("Not yet implemented")
        //delete  event from database
    }

    private val args by navArgs<DailyCheckFragmentArgs>()

    override val titleRes: Int = 3

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val events = mutableMapOf<LocalDate, List<Event>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.daily_check_recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = eventsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        view.calendarView.apply {
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        if (savedInstanceState == null) {
            view.calendarView.post {
                // Show today's events initially.
                selectDate(today)
            }
        }


        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }
                }
            }
        }

        view.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day


            }


        }

        view.calendarView.monthScrollListener = {
            homeActivityToolbar.title = if (it.year == today.year) {
                titleSameYearFormatter.format(it.yearMonth)
            } else {
                titleFormatter.format(it.yearMonth)
            }

            // Select the first day of the month when
            // we scroll to a new month.
            selectDate(it.yearMonth.atDay(1))
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = Example3CalendarHeaderBinding.bind(view).legendLayout.root
        }
        view.calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }
                        .forEachIndexed { index, tv ->
                            tv.text = daysOfWeek[index].name.first().toString()
                            tv.setTextColorRes(R.color.example_3_black)
                        }
                }
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.calendar_subject_cardview, container, false)


        return view
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { calendarView.notifyDateChanged(it) }
            calendarView.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun updateAdapterForDate(date: LocalDate) {
        eventsAdapter.apply { //viewmodel로 observe되면 실행되게

        }

    }
}

