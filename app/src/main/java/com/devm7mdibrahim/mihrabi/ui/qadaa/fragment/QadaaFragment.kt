package com.devm7mdibrahim.mihrabi.ui.qadaa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devm7mdibrahim.mihrabi.databinding.FragmentQadaaBinding
import com.devm7mdibrahim.mihrabi.ui.qadaa.viewModel.QadaaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QadaaFragment : Fragment() {

    private val qadaaViewModel: QadaaViewModel by viewModels()
    private lateinit var qadaaBinding: FragmentQadaaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qadaaBinding = FragmentQadaaBinding.inflate(inflater, container, false)
        qadaaBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }
        init()
        return qadaaBinding.root
    }

    private fun init() {
        qadaaBinding.plus1.setOnClickListener {
            if (qadaaBinding.qadaaEt1.text.toString().isEmpty()) qadaaBinding.qadaaEt1.setText("0")

            qadaaBinding.qadaaEt1.setText(
                (qadaaBinding.qadaaEt1.text.toString().toInt() + 1).toString()
            )
        }
        qadaaBinding.plus2.setOnClickListener {
            if (qadaaBinding.qadaaEt2.text.toString().isEmpty()) qadaaBinding.qadaaEt2.setText("0")
            qadaaBinding.qadaaEt2.setText(
                (qadaaBinding.qadaaEt2.text.toString().toInt() + 1).toString()
            )
        }
        qadaaBinding.plus3.setOnClickListener {
            if (qadaaBinding.qadaaEt3.text.toString().isEmpty()) qadaaBinding.qadaaEt3.setText("0")
            qadaaBinding.qadaaEt3.setText(
                (qadaaBinding.qadaaEt3.text.toString().toInt() + 1).toString()
            )
        }
        qadaaBinding.plus4.setOnClickListener {
            if (qadaaBinding.qadaaEt4.text.toString().isEmpty()) qadaaBinding.qadaaEt4.setText("0")
            qadaaBinding.qadaaEt4.setText(
                (qadaaBinding.qadaaEt4.text.toString().toInt() + 1).toString()
            )
        }
        qadaaBinding.plus5.setOnClickListener {
            if (qadaaBinding.qadaaEt5.text.toString().isEmpty()) qadaaBinding.qadaaEt5.setText("0")
            qadaaBinding.qadaaEt5.setText(
                (qadaaBinding.qadaaEt5.text.toString().toInt() + 1).toString()
            )
        }
        qadaaBinding.plus6.setOnClickListener {
            if (qadaaBinding.qadaaEt6.text.toString().isEmpty()) qadaaBinding.qadaaEt6.setText("0")
            qadaaBinding.qadaaEt6.setText(
                (qadaaBinding.qadaaEt6.text.toString().toInt() + 1).toString()
            )
        }



        qadaaBinding.minus1.setOnClickListener {
            if (qadaaBinding.qadaaEt1.text.toString().isEmpty()) qadaaBinding.qadaaEt1.setText("0")
            qadaaBinding.qadaaEt1.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt1.setText((it - 1).toString()) }
        }
        qadaaBinding.minus2.setOnClickListener {
            if (qadaaBinding.qadaaEt2.text.toString().isEmpty()) qadaaBinding.qadaaEt2.setText("0")
            qadaaBinding.qadaaEt2.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt2.setText((it - 1).toString()) }
        }
        qadaaBinding.minus3.setOnClickListener {
            if (qadaaBinding.qadaaEt3.text.toString().isEmpty()) qadaaBinding.qadaaEt3.setText("0")
            qadaaBinding.qadaaEt3.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt3.setText((it - 1).toString()) }
        }
        qadaaBinding.minus4.setOnClickListener {
            if (qadaaBinding.qadaaEt4.text.toString().isEmpty()) qadaaBinding.qadaaEt4.setText("0")
            qadaaBinding.qadaaEt4.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt4.setText((it - 1).toString()) }
        }
        qadaaBinding.minus5.setOnClickListener {
            if (qadaaBinding.qadaaEt5.text.toString().isEmpty()) qadaaBinding.qadaaEt5.setText("0")
            qadaaBinding.qadaaEt5.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt5.setText((it - 1).toString()) }
        }
        qadaaBinding.minus6.setOnClickListener {
            if (qadaaBinding.qadaaEt6.text.toString().isEmpty()) qadaaBinding.qadaaEt6.setText("0")
            qadaaBinding.qadaaEt6.text.toString().toInt()
                .let { if (it > 0) qadaaBinding.qadaaEt6.setText((it - 1).toString()) }
        }
    }

    override fun onResume() {
        super.onResume()

        qadaaBinding.qadaaEt1.setText(qadaaViewModel.getQadaaData(1).toString())
        qadaaBinding.qadaaEt2.setText(qadaaViewModel.getQadaaData(2).toString())
        qadaaBinding.qadaaEt3.setText(qadaaViewModel.getQadaaData(3).toString())
        qadaaBinding.qadaaEt4.setText(qadaaViewModel.getQadaaData(4).toString())
        qadaaBinding.qadaaEt5.setText(qadaaViewModel.getQadaaData(5).toString())
        qadaaBinding.qadaaEt6.setText(qadaaViewModel.getQadaaData(6).toString())
    }

    override fun onPause() {
        super.onPause()
        var text1 = qadaaBinding.qadaaEt1.text.toString().trim()
        var text2 = qadaaBinding.qadaaEt2.text.toString().trim()
        var text3 = qadaaBinding.qadaaEt3.text.toString().trim()
        var text4 = qadaaBinding.qadaaEt4.text.toString().trim()
        var text5 = qadaaBinding.qadaaEt5.text.toString().trim()
        var text6 = qadaaBinding.qadaaEt6.text.toString().trim()

        if (text1.isEmpty()) text1 = "0"
        if (text2.isEmpty()) text2 = "0"
        if (text3.isEmpty()) text3 = "0"
        if (text4.isEmpty()) text4 = "0"
        if (text5.isEmpty()) text5 = "0"
        if (text6.isEmpty()) text6 = "0"

        qadaaViewModel.setQadaaData(1, text1.toInt())
        qadaaViewModel.setQadaaData(2, text2.toInt())
        qadaaViewModel.setQadaaData(3, text3.toInt())
        qadaaViewModel.setQadaaData(4, text4.toInt())
        qadaaViewModel.setQadaaData(5, text5.toInt())
        qadaaViewModel.setQadaaData(6, text6.toInt())
    }
}