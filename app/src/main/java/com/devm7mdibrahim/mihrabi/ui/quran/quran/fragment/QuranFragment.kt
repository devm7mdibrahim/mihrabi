package com.devm7mdibrahim.mihrabi.ui.quran.quran.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.devm7mdibrahim.mihrabi.databinding.FragmentQuranBinding
import com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter.PageAdapter
import com.devm7mdibrahim.mihrabi.ui.quran.quran.viewModel.QuranViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.Constants.SURAH_PAGES_NUMBERS
import com.devm7mdibrahim.mihrabi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranFragment : Fragment() {

    private val surahNumber: Int by lazy {
        arguments?.run { getInt(Constants.SURAH_NUMBER) }!!
    }

    private lateinit var pageAdapter: PageAdapter
    private val quranViewModel: QuranViewModel by viewModels()
    private lateinit var quranBinding: FragmentQuranBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        quranBinding = FragmentQuranBinding.inflate(inflater, container, false)
        initRecyclerView()
        fetchQuranPages()
        return quranBinding.root
    }

    private fun initRecyclerView() {
        pageAdapter = PageAdapter()
        quranBinding.quranRecyclerView.apply {
            setHasFixedSize(true)
            LinearSnapHelper().attachToRecyclerView(this)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            adapter = pageAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun fetchQuranPages() {
        quranViewModel.quranPages.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    it.data.let { quranPagesList ->
                        pageAdapter.submitList(quranPagesList)
                        quranBinding.quranRecyclerView.scrollToPosition(SURAH_PAGES_NUMBERS[surahNumber] - 1)
                        quranBinding.quranLoader.visibility = View.GONE
                    }
                }

                is DataState.Loading -> {
                    quranBinding.quranLoader.visibility = View.VISIBLE
                }

                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_LONG).show()
                    quranBinding.quranLoader.visibility = View.GONE
                }
            }
        })
    }
}