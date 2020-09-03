package com.devm7mdibrahim.mihrabi.ui.quran.quran.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.base.BaseFragment
import com.devm7mdibrahim.mihrabi.databinding.FragmentQuranBinding
import com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter.PageAdapter
import com.devm7mdibrahim.mihrabi.ui.quran.quran.viewModel.QuranViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.Constants.SURAH_PAGES_NUMBERS
import com.devm7mdibrahim.mihrabi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranFragment : BaseFragment<FragmentQuranBinding>() {

    private val surahNumber: Int by lazy {
        arguments?.run { getInt(Constants.SURAH_NUMBER) }!!
    }
    private val pageAdapter: PageAdapter by lazy {
        PageAdapter()
    }

    private val quranViewModel: QuranViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fetchQuranPages()
    }

    private fun initRecyclerView() {
        binding?.quranRecyclerView?.run {
            setHasFixedSize(true)
            LinearSnapHelper().attachToRecyclerView(this)
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            adapter = pageAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun fetchQuranPages() {
        quranViewModel.quranPages.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    pageAdapter.submitList(it.data)
                    binding?.quranRecyclerView?.scrollToPosition(SURAH_PAGES_NUMBERS[surahNumber] - 1)
                    showLoading(View.GONE)
                }

                is DataState.Loading -> {
                    showLoading(View.VISIBLE)
                }

                is DataState.Error -> {
                    showToast(it.exception)
                    showLoading(View.GONE)
                }
            }
        })
    }

    private fun showLoading(visibility: Int) {
        binding?.quranLoader?.visibility = visibility
    }

    override fun getFragmentView(): Int = R.layout.fragment_quran
}