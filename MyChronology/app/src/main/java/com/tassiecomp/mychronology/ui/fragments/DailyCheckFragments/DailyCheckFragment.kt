package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.animation.ValueAnimator
import android.app.ProgressDialog.show
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.yearMonth
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.DailyCheckTodoAdapter
import com.tassiecomp.mychronology.databinding.CalendarDayLayoutBinding
import com.tassiecomp.mychronology.databinding.FragmentDailyCheckBinding
import com.tassiecomp.mychronology.models.SubjectItem
import com.tassiecomp.mychronology.ui.MainActivity
import com.tassiecomp.mychronology.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.calendar_day_layout.*
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import kotlinx.android.synthetic.main.fragment_manage_subject.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.subject_selected_cardview.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*


class DailyCheckFragment : Fragment() {


    private var selectedDate: LocalDate? = null
    val today = LocalDate.now()
    val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private lateinit var MainViewModelHomeFragment: MainViewModel
    private lateinit var binding: FragmentDailyCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_check, container, false)


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDailyCheckBinding.bind(view)
        MainViewModelHomeFragment = (activity as MainActivity).MainViewModelHomeFragment
        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        Log.d("TAGG", "current month: $currentMonth")
//

        class MonthViewContainer(view: View) : ViewContainer(view) {
        }

        binding.calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {

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

//                if (day.owner == DayOwner.THIS_MONTH) {
//                    textView.makeVisible()
//                    when (day.date) {
//                        today -> {
//                            textView.setTextColorRes(R.color.white)
//                            textView.setBackgroundResource(R.drawable.calendar_today_bg)
//                            dotView.makeInVisible()
//
//                        }
//                        selectedDate -> {
//                            textView.setTextColorRes(R.color.lightBlue)
//                            textView.setBackgroundResource(R.drawable.calendar_selected_bg)
//                            dotView.makeInVisible()
//                        }
//                        else -> {
//                            textView.setTextColorRes(R.color.black)
//                            textView.background = null
////                            dotView.isVisible = events [day.date].orEmpty().isNotEmpty()
//                        }
//                    }
//                } else {
//                    textView.setTextColorRes(R.color.black)
//                    dotView.makeInVisible()
////                    textView.background = null
//
//
//                }

            }


        }
        binding.calendarView.apply {

            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)

        }

        //scroll listener
        binding.calendarView.monthScrollListener = {
            if (binding.calendarView.maxRowCount == 6) {
                binding.YearText.text = it.yearMonth.year.toString()
                binding.MonthText.text = titleSameYearFormatter.format(it.yearMonth)
            } else {
                val firstDate = it.weekDays.first().first().date
                val lastDate = it.weekDays.last().last().date
                if (firstDate.yearMonth == lastDate.yearMonth) {
                    binding.YearText.text = firstDate.yearMonth.year.toString()
                    binding.MonthText.text = titleSameYearFormatter.format(firstDate)
                } else {
                    binding.MonthText.text =
                        "${titleSameYearFormatter.format(firstDate)} - ${
                            titleSameYearFormatter.format(
                                lastDate
                            )
                        }"
                    if (firstDate.year == lastDate.year) {
                        binding.YearText.text = firstDate.yearMonth.year.toString()
                    } else {
                        binding.YearText.text =
                            "${firstDate.yearMonth.year} - ${lastDate.yearMonth.year}"
                    }
                }
            }
        }

        //weekModeCheckBox
        binding.WeekModeCheckbox.setOnCheckedChangeListener { _, monthToWeek ->
            val firstDate = binding.calendarView.findFirstVisibleDay()?.date
                ?: return@setOnCheckedChangeListener
            val lastDate =
                binding.calendarView.findLastVisibleDay()?.date ?: return@setOnCheckedChangeListener

            val oneWeekHeight = binding.calendarView.daySize.height
            val oneMonthHeight = oneWeekHeight * 6

            val oldHeight = if (monthToWeek) oneMonthHeight else oneWeekHeight
            val newHeight = if (monthToWeek) oneWeekHeight else oneMonthHeight

            val animator = ValueAnimator.ofInt(oldHeight, newHeight)
            animator.addUpdateListener { animator ->
                binding.calendarView.updateLayoutParams {
                    height = animator.animatedValue as Int
                }

            }
            animator.doOnStart {
                if (!monthToWeek) {
                    binding.calendarView.updateMonthConfiguration(
                        inDateStyle = InDateStyle.ALL_MONTHS,
                        maxRowCount = 6,
                        hasBoundaries = true
                    )
                }
            }
            animator.doOnEnd {
                if (monthToWeek) {
                    binding.calendarView.updateMonthConfiguration(
                        inDateStyle = InDateStyle.FIRST_MONTH,
                        maxRowCount = 1,
                        hasBoundaries = false
                    )
                }
                if (monthToWeek) {
                    // We want the first visible day to remain
                    // visible when we change to week mode.
                    binding.calendarView.scrollToDate(today)
                } else {
                    // When changing to month mode, we choose current
                    // month if it is the only one in the current frame.
                    // if we have multiple months in one frame, we prefer
                    // the second one unless it's an outDate in the last index.
                    if (firstDate.yearMonth == lastDate.yearMonth) {
                        binding.calendarView.scrollToMonth(today.yearMonth)
                    } else {
                        // We compare the next with the last month on the calendar so we don't go over.
                        binding.calendarView.scrollToMonth(
                            minOf(
                                firstDate.yearMonth.next, currentMonth.plusMonths(10)
                            )
                        )
                    }
                }
            }
            animator.duration = 250
            animator.start()

        }




    }




//    fun setupTodoRecyclerView() {
//        DailyCheckTodoAdapter = DailyCheckTodoAdapter()
//        daily_check_recyclerView.apply {
//            adapter = DailyCheckTodoAdapter
//            layoutManager = LinearLayoutManager(requireContext())
//        }
//    }


    private fun selectDate(date: LocalDate) {
        val createdDate: String = titleSameYearFormatter.format(date)
        if (selectedDate != date) {
            updateAdapterForDate(createdDate)

        }
    }

    private fun updateAdapterForDate(createdDate: String) {
//        MainViewModelHomeFragment.selectedDateData(createdDate)
//            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//                (daily_check_recyclerView.adapter as DailyCheckTodoAdapter).submitList(it)
//            })

    }

    private fun updateDot(date: LocalDate) {
        val day: String = titleSameYearFormatter.format(date)
        MainViewModelHomeFragment.selectedDateData(day).observe(viewLifecycleOwner) {
            var count = it.size
            countView.text = count.toString()
        }

    }





}










