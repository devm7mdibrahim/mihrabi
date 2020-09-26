package com.devm7mdibrahim.mihrabi.ui.prayer_times.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentPrayerTimesBinding
import com.devm7mdibrahim.mihrabi.ui.prayer_times.adapter.PrayersAdapter
import com.devm7mdibrahim.mihrabi.ui.prayer_times.viewModel.PrayerTimesViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate
import org.threeten.bp.chrono.HijrahDate
import org.threeten.bp.format.DateTimeFormatter
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PrayerTimesFragment : Fragment() {

    private val prayerTimesViewModel: PrayerTimesViewModel by viewModels()
    private lateinit var prayersAdapter: PrayersAdapter
    private lateinit var prayerTimesBinding: FragmentPrayerTimesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prayerTimesBinding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        initRecyclerView()
        getUserLocation()
        getHijriDate()

        return prayerTimesBinding.root
    }

    private fun initRecyclerView() {
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        getDrawable(
            requireContext(),
            R.drawable.item_devider
        )?.let { itemDecoration.setDrawable(it) }
        prayersAdapter = PrayersAdapter()

        prayerTimesBinding.prayerTimesRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.stackFromEnd = true
            layoutManager = linearLayoutManager

            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
            adapter = prayersAdapter
        }
    }


    private fun getHijriDate() {
        try {
            val currentDateTime = LocalDateTime.now()

            val date: LocalDate = LocalDate.of(
                currentDateTime.year,
                currentDateTime.monthValue,
                currentDateTime.dayOfMonth
            )

            val hijriDate: HijrahDate = HijrahDate.from(date)

            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val formattedDate: String = formatter.format(hijriDate)

            displayCurrentHijriDate(formattedDate)

        } catch (e: Exception) {
            Log.d(TAG, "getHijriDate: " + e.message)
        }
    }

    private fun displayCurrentHijriDate(formattedDate: String) {
        try {
            val splitArray = formattedDate.split("/")
            val displayedHijriDate =
                NumberHelper.getArabicNumber(splitArray[2].toInt()) + " " +
                        Constants.HIJRI_MONTHS_IN_ARABIC[splitArray[1].toInt() - 1] + " " +
                        NumberHelper.getArabicNumber(splitArray[0].toInt()) + " هـ"


            prayerTimesBinding.hijriDate.text = displayedHijriDate
        } catch (e: Exception) {
            Log.d(TAG, "displayCurrentHijriDate: ${e.message}")
        }
    }

    private fun getUserLocation() {
        val userLatitude = prayerTimesViewModel.getUserLatitude()
        val userLongitude = prayerTimesViewModel.getUserLongitude()
        val userCountry = prayerTimesViewModel.getUserCountry()

        displayUserCountry(userCountry)
        getPrayerTimes(userLatitude, userLongitude)
    }

    private fun displayUserCountry(userCountry: String) {
        prayerTimesBinding.userCity.text = userCountry
    }

    private fun getPrayerTimes(userLatitude: Double, userLongitude: Double) {
        val mGMTOffset = GregorianCalendar().timeZone.rawOffset
        val gmtDiff = TimeUnit.HOURS.convert(mGMTOffset.toLong(), TimeUnit.MILLISECONDS)
        val today = SimpleDate(GregorianCalendar())
        val location = Location(userLatitude, userLongitude, gmtDiff.toDouble(), 0)
        val azan = Azan(location, Method.EGYPT_SURVEY)
        val azanTimes = azan.getPrayerTimes(today)

        prayersAdapter.setPrayers(azanTimes.times)
    }
}