package com.tassiecomp.mychronology.ui.fragments.DailyCheckFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.adapters.DailyRecycleviewAdapter
import com.tassiecomp.mychronology.adapters.Event
import kotlinx.android.synthetic.main.fragment_daily_check.*
import kotlinx.android.synthetic.main.fragment_daily_check.view.*
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.time.LocalDate
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

    override val titleRes: Int = R.string.example_3_title

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val events = mutableMapOf<LocalDate, List<Event>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.daily_check_recyclerView.apply{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.calendar_subject_cardview, container, false)


        return view
    }


}