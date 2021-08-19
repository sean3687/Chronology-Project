package com.tassiecomp.mychronology.ui.fragments.HomeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.badoualy.datepicker.MonthView
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import java.util.*


class DailyCheckFragment : Fragment(R.layout.fragment_daily_check) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_check, container, false)

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        view.apply {
            timeLine.setFirstVisibleDate()
            timeLine.setLastVisibleDate(day,month,year)

            timeLine.setDateLabelAdapter(MonthView.DateLabelAdapter { calendar, index ->
                Integer.toString(
                    calendar[Calendar.MONTH] + 1
                ) + "/" + calendar[Calendar.YEAR] % 2000
            })
        }


        return view
    }


}