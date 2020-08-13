package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.fragments

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
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentFadlElsalahBinding
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.adapters.FadlElsalahAdapter
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.viewModel.FadlElsalahViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FadlElsalahFragment : Fragment(), ItemClickListener {

    private lateinit var fadlList: List<Fadl>
    private val fadlElsalahViewModel by viewModels<FadlElsalahViewModel>()
    private lateinit var fadlElsalahBinding: FragmentFadlElsalahBinding

    private val fadlAdapter: FadlElsalahAdapter by lazy {
        FadlElsalahAdapter(this)
    }

    private val navController: NavController by lazy {
        view?.let { Navigation.findNavController(it) }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fadlElsalahBinding = FragmentFadlElsalahBinding.inflate(inflater, container, false)
        initRV()
        getFadlData()
        return fadlElsalahBinding.root
    }

    private fun initRV() {
        fadlElsalahBinding.fadlElsalahRv.apply {
            adapter = fadlAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fadlElsalahBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }
    }

    private fun getFadlData() {
        fadlElsalahViewModel.getFadlElsalahList(0)
        fadlElsalahViewModel.fadl.observe(viewLifecycleOwner, Observer {dataState ->
            when (dataState) {
                is DataState.Success -> {
                    fadlList = dataState.data
                    fadlAdapter.submitList(dataState.data)
                    fadlElsalahBinding.fadlLoader.visibility = View.GONE
                }

                is DataState.Error -> {
                    fadlElsalahBinding.fadlLoader.visibility = View.GONE
                    fadlElsalahBinding.errorMessageTv.visibility = View.VISIBLE
                    fadlElsalahBinding.errorMessageTv.text = dataState.exception
                }

                is DataState.Loading -> {
                    fadlElsalahBinding.fadlLoader.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val bundle = bundleOf(Constants.FADL_TYPE to position, Constants.FADL_NAME to fadlList[position].text)
        navController.navigate(R.id.action_fadlElsalahFragment_to_fadlElsalahDetailFragment, bundle)
    }
}