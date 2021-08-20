package com.tassiecomp.mychronology.ui.fragments.HomeFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.badoualy.datepicker.MonthView
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import java.text.SimpleDateFormat
import java.util.*


class DailyCheckFragment : Fragment(R.layout.fragment_daily_check) {

    private val args by navArgs<DailyCheckFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_check, container, false)

        val title = args.selectedSubject.title
        val color = args.selectedSubject.color
        val created = args.selectedSubject.created

        Log.d("TAGG", "$created")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        val parse = sdf.parse(created)
        val createdCalendar = Calendar.getInstance()
        createdCalendar.time = parse

        val day_first = createdCalendar[Calendar.DATE]
        val month_first = createdCalendar[Calendar.MONTH]
        val year_first = createdCalendar[Calendar.YEAR]

        val day_last = c.get(Calendar.DAY_OF_MONTH)
        val month_last = c.get(Calendar.MONTH)
        val year_last = c.get(Calendar.YEAR)


        view.apply {
            timeLine.setFirstVisibleDate(day_first, month_first, year_first)
            timeLine.setLastVisibleDate(day_last, month_last, year_last)

            timeLine.setDateLabelAdapter(MonthView.DateLabelAdapter { calendar, index ->
                Integer.toString(
                    calendar[Calendar.MONTH] + 1
                ) + "/" + calendar[Calendar.YEAR] % 2000
            })
        }


        return view
    }


}