package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devm7mdibrahim.mihrabi.databinding.FragmentFadlElsalahDetailBinding
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.adapters.FadlElsalahDetailAdapter
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.viewModel.FadlElsalahViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FadlElsalahDetailFragment : Fragment() {

    private lateinit var fadlBinding: FragmentFadlElsalahDetailBinding


    private val fadlDetailsAdapter: FadlElsalahDetailAdapter by lazy {
        FadlElsalahDetailAdapter()
    }

    private val fadlElsalahViewModel by viewModels<FadlElsalahViewModel>()

    private val fadlType: Int by lazy {
        arguments?.run { getInt(Constants.FADL_TYPE) }!!
    }

    private val fadlName: String by lazy {
        arguments?.run { getString(Constants.FADL_NAME) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fadlBinding = FragmentFadlElsalahDetailBinding.inflate(inflater, container, false)
        initRV()
        getFadlData()
        return fadlBinding.root
    }

    private fun initRV() {
        fadlBinding.fadlElsalahDetailRv.apply {
            adapter = fadlDetailsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fadlBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }

        fadlBinding.fadlNameTv.text = fadlName
    }

    private fun getFadlData() {
        fadlElsalahViewModel.getFadlElsalahList(fadlType + 1)
        fadlElsalahViewModel.fadl.observe(viewLifecycleOwner, Observer {
            when(it){
                is DataState.Success -> {
                    fadlDetailsAdapter.submitList(it.data)
                    fadlBinding.fadlDetailLoader.visibility = View.GONE
                }

                is DataState.Error -> {
                    fadlBinding.fadlDetailLoader.visibility = View.GONE
                    fadlBinding.errorMessageTv.visibility = View.VISIBLE
                    fadlBinding.errorMessageTv.text = it.exception
                }

                is DataState.Loading -> {
                    fadlBinding.fadlDetailLoader.visibility = View.VISIBLE
                }
            }
        })
    }

}