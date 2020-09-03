package com.devm7mdibrahim.mihrabi.ui.quran.surahList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentSurahListBinding
import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.ui.quran.surahList.adapter.SurahListAdapter
import com.devm7mdibrahim.mihrabi.ui.quran.surahList.viewModel.SurahListViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants.SURAH_NUMBER
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahListFragment : Fragment(), ItemClickListener {

    private val surahListViewModel: SurahListViewModel by viewModels()
    private lateinit var surahListBinding: FragmentSurahListBinding

    private val surahListAdapter: SurahListAdapter by lazy {
        SurahListAdapter(this)
    }

    private val navController: NavController by lazy {
        view?.let { Navigation.findNavController(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        surahListBinding = FragmentSurahListBinding.inflate(inflater, container, false)
        surahListBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }
        return surahListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fetchData()
    }

    private fun initRecyclerView() {
        surahListBinding.surahRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = surahListAdapter
        }
    }

    private fun fetchData() {
        surahListViewModel.surah.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<List<Surah>> -> {
                    it.data.let { azkarList ->
                        surahListAdapter.submitList(azkarList)
                        surahListBinding.surahLoader.visibility = View.GONE
                    }
                }

                is DataState.Loading -> {
                    surahListBinding.surahLoader.visibility = View.VISIBLE
                }

                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_LONG).show()
                    surahListBinding.surahLoader.visibility = View.GONE
                }
            }
        })
    }


    override fun onItemClick(position: Int) {
        val bundle = bundleOf(SURAH_NUMBER to position)
        navController.navigate(R.id.action_SurahListFragment_to_QuranFragment, bundle)
    }
}