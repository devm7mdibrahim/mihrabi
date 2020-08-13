package com.devm7mdibrahim.mihrabi.ui.imaniat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentImaniatBinding


class ImaniatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = FragmentImaniatBinding.inflate(layoutInflater, container, false)
        view.pdfViewer.fromAsset("kamel_elsourah.pdf").load()
        return view.root
    }
}