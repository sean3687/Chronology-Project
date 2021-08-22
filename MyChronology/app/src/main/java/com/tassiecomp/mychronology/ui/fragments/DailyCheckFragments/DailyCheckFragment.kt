package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields

import java.util.*


class DailyCheckFragment : Fragment(R.layout.fragment_daily_check) {

    private val args by navArgs<DailyCheckFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_check, container, false)

        view.apply{
            view.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
                // Called only when a new container is needed.
                override fun create(view: View) = DayViewContainer(view)

                // Called every time we need to reuse a container.
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    container.textView.text = day.date.dayOfMonth.toString()
                }
            }

            val currentMonth = YearMonth.now()
            val firstMonth = currentMonth.minusMonths(10)
            val lastMonth = currentMonth.plusMonths(10)
            val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
            calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
            calendarView.scrollToMonth(currentMonth)

        }










//        val title = args.selectedSubject.title
//        val color = args.selectedSubject.color
//        val created = args.selectedSubject.created
//
//        Log.d("TAGG", "$created")
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val c = Calendar.getInstance()
//        val parse = sdf.parse(created)
//        val createdCalendar = Calendar.getInstance()
//        createdCalendar.time = parse
//
//        val day_first = createdCalendar[Calendar.DATE]
//        val month_first = createdCalendar[Calendar.MONTH]
//        val year_first = createdCalendar[Calendar.YEAR]
//
//        Log.d("TAGGG", "$day_first, $month_first, $year_first")
//
//        val day_last = c.get(Calendar.DAY_OF_MONTH)
//        val month_last = c.get(Calendar.MONTH)
//        val year_last = c.get(Calendar.YEAR)
//
//        Log.d("TAGGG", "$day_last, $month_last, $year_last")

        return view
    }


}