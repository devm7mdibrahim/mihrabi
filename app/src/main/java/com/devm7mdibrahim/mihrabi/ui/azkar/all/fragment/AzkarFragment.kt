package com.devm7mdibrahim.mihrabi.ui.azkar.all.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentAzkarBinding
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.ui.azkar.all.adapter.AzkarAdapter
import com.devm7mdibrahim.mihrabi.ui.azkar.viewModel.AzkarViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants.AZKAR_NAME
import com.devm7mdibrahim.mihrabi.utils.Constants.AZKAR_TYPE
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarFragment : Fragment(), ItemClickListener {

    private val azkarViewModel: AzkarViewModel by viewModels()
    private lateinit var azkarBinding: FragmentAzkarBinding
    private lateinit var azkarList: List<Azkar>

    private val azkarAdapter: AzkarAdapter by lazy {
        AzkarAdapter(this)
    }

    private val navController: NavController by lazy {
        view?.let { Navigation.findNavController(it) }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        azkarBinding = FragmentAzkarBinding.inflate(inflater, container, false)
        initRecyclerView()
        fetchAzkar()
        return azkarBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        azkarBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }
    }

    private fun initRecyclerView() {
        azkarBinding.azkarRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
            adapter = azkarAdapter
        }
    }


    private fun fetchAzkar() {
        azkarViewModel.fetchAzkar(0)
        azkarViewModel.azkar.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    azkarList = dataState.data
                    azkarAdapter.submitList(dataState.data)
                    azkarBinding.azkarLoader.visibility = View.GONE
                }

                is DataState.Error -> {
                    azkarBinding.azkarLoader.visibility = View.GONE
                    azkarBinding.errorMessageTv.visibility = View.VISIBLE
                    azkarBinding.errorMessageTv.text = dataState.exception
                }

                is DataState.Loading -> {
                    azkarBinding.azkarLoader.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onItemClick(position: Int) {
        val bundle = bundleOf(AZKAR_TYPE to position, AZKAR_NAME to azkarList[position].text)
        navController.navigate(R.id.action_azkarFragment_to_azkarDetailFragment, bundle)
    }
}