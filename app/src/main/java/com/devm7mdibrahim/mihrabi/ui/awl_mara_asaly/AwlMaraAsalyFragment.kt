package com.devm7mdibrahim.mihrabi.ui.awl_mara_asaly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devm7mdibrahim.mihrabi.databinding.FragmentAwlMaraAsalyBinding

class AwlMaraAsalyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = FragmentAwlMaraAsalyBinding.inflate(inflater, container, false)
        view.pdfViewer.fromAsset("awl_mara_asaly.pdf").load()
        return view.root
    }
}