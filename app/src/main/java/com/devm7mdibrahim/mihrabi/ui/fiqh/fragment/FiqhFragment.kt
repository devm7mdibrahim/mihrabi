package com.devm7mdibrahim.mihrabi.ui.fiqh.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devm7mdibrahim.mihrabi.databinding.FragmentFiqhBinding
import com.devm7mdibrahim.mihrabi.ui.fiqh.adapter.FiqhAdapter
import com.devm7mdibrahim.mihrabi.ui.fiqh.viewModel.FiqhViewModel
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiqhFragment : Fragment() {

    private val fiqhViewModel by viewModels<FiqhViewModel>()
    private lateinit var fiqhBinding: FragmentFiqhBinding

    private val fiqhAdapter: FiqhAdapter by lazy {
        FiqhAdapter()
    }

    private val fiqhType: Int by lazy {
        arguments?.run {
            getInt(Constants.FIQH_TYPE)
        }!!
    }

    private val fiqhName: String by lazy {
        arguments?.run {
            getString(Constants.FIQH_NAME)
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fiqhBinding = FragmentFiqhBinding.inflate(inflater, container, false)
        initRV()
        fetchFiqhData()
        return fiqhBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fiqhBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }

        fiqhBinding.fiqhName.text = fiqhName
    }

    private fun initRV() {
        fiqhBinding.fiqhRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = fiqhAdapter
            setHasFixedSize(true)
        }
    }

    private fun fetchFiqhData() {
        fiqhViewModel.fetchData(fiqhType)
        fiqhViewModel.fiqh.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    it.data.let { fiqhList ->
                        fiqhAdapter.submitFiqhList(fiqhList)
                        fiqhBinding.fiqhLoader.visibility = View.GONE
                    }
                }

                is DataState.Loading -> {
                    fiqhBinding.fiqhLoader.visibility = View.VISIBLE
                }

                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_LONG).show()
                    fiqhBinding.fiqhLoader.visibility = View.GONE
                }
            }
        })
    }
}
