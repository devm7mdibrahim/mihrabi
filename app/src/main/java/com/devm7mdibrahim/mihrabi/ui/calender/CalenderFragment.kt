package com.devm7mdibrahim.mihrabi.ui.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devm7mdibrahim.mihrabi.databinding.FragmentCalenderBinding
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import com.github.eltohamy.materialhijricalendarview.CalendarDay
import com.github.eltohamy.materialhijricalendarview.MaterialHijriCalendarView
import com.github.eltohamy.materialhijricalendarview.OnDateSelectedListener
import java.util.*

class CalenderFragment : Fragment(), OnDateSelectedListener {

    private lateinit var binding: FragmentCalenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.hijriDateTv.text = getSelectedDatesString()
        binding.calendarView.setOnDateChangedListener(this)

        binding.backImgBtn.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onDateSelected(
        widget: MaterialHijriCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {
        binding.hijriDateTv.text = getSelectedDatesString()
    }

    private fun getSelectedDatesString(): String? {
        val date: CalendarDay = binding.calendarView.selectedDate

        return NumberHelper.getArabicNumber(date.calendar[Calendar.DAY_OF_MONTH]) + " " +
                date.calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale("ar")) +
                " " + NumberHelper.getArabicNumber(date.year) + " هــ"
    }
}