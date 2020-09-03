package com.devm7mdibrahim.mihrabi.ui.imaniat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentImaniatBinding
import com.devm7mdibrahim.mihrabi.ui.imaniat.viewmodel.ImaniatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImaniatFragment : Fragment() {
    private val imaniatViewModel by viewModels<ImaniatViewModel>()
    private lateinit var binding: FragmentImaniatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImaniatBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (imaniatViewModel.getImaniatFirstTime()) {
            binding.continueTv.setOnClickListener {
                imaniatViewModel.setImaniatFirstTime(false)
                showBook()
            }
        } else {
            showBook()
        }
    }

    private fun showBook() {
        binding.introductionLayout.visibility = View.GONE
        binding.pdfViewer.visibility = View.VISIBLE
        binding.pdfViewer.fromAsset("kamel_elsourah.pdf").load()
    }
}