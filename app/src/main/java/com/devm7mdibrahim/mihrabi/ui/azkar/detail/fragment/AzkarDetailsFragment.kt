package com.devm7mdibrahim.mihrabi.ui.azkar.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devm7mdibrahim.mihrabi.databinding.FragmentAzkarDetailsBinding
import com.devm7mdibrahim.mihrabi.ui.azkar.detail.adapter.AzkarDetailsAdapter
import com.devm7mdibrahim.mihrabi.ui.azkar.viewModel.AzkarViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarDetailsFragment : Fragment() {

    private val azkarViewModel: AzkarViewModel by viewModels()
    private lateinit var azkarDetailBinding: FragmentAzkarDetailsBinding

    private val azkarAdapter: AzkarDetailsAdapter by lazy {
        AzkarDetailsAdapter()
    }

    private val type: Int by lazy {
        arguments?.run { getInt(Constants.AZKAR_TYPE) }!!
    }

    private val azkarName: String by lazy {
        arguments?.run { getString(Constants.AZKAR_NAME) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        azkarDetailBinding = FragmentAzkarDetailsBinding.inflate(inflater, container, false)
        initRecyclerView()
        fetchAzkar()
        return azkarDetailBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        azkarDetailBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }

        azkarDetailBinding.azkarName.text = azkarName
    }

    private fun initRecyclerView() {
        azkarDetailBinding.azkarDetailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = azkarAdapter
        }
    }

    private fun fetchAzkar() {
        azkarViewModel.fetchAzkar(type + 1)
        azkarViewModel.azkar.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    azkarAdapter.submitList(it.data)
                    azkarDetailBinding.azkarDetailLoader.visibility = View.GONE
                }

                is DataState.Loading -> {
                    azkarDetailBinding.azkarDetailLoader.visibility = View.VISIBLE
                }

                is DataState.Error -> {
                    azkarDetailBinding.azkarDetailLoader.visibility = View.GONE
                    azkarDetailBinding.errorMessageTv.visibility = View.VISIBLE
                    azkarDetailBinding.errorMessageTv.text = it.exception
                }
            }
        })
    }
}