package com.devm7mdibrahim.mihrabi.ui.misbaha.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentMisbahaBinding
import com.devm7mdibrahim.mihrabi.ui.misbaha.viewModel.MisbahaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MisbahaFragment : Fragment() {
    private val misbahaViewModel: MisbahaViewModel by viewModels()
    private lateinit var misbahaBinding: FragmentMisbahaBinding

    private val vibrator: Vibrator by lazy {
        requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        misbahaBinding = FragmentMisbahaBinding.inflate(layoutInflater, container, false)
        return misbahaBinding.root
    }

    override fun onResume() {
        super.onResume()
        misbahaBinding.misbahaBtn.text = misbahaViewModel.getMisbahaCount().toString()

        if (misbahaViewModel.isVibrateOn()) {
            misbahaBinding.misbahaVibrationBtn.setBackgroundResource(R.drawable.vibrate_on)
        } else {
            misbahaBinding.misbahaVibrationBtn.setBackgroundResource(R.drawable.vibrate_off)
        }
        
        misbahaBinding.misbahaBtn.run {
            setOnClickListener {
                text = (text.toString().toInt() + 1).toString()
                if (misbahaViewModel.isVibrateOn()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                200,
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        )
                    } else {
                        vibrator.vibrate(200)
                    }
                }
            }
        }

        misbahaBinding.backImgBtn.setOnClickListener {
            activity?.run {
                onBackPressed()
            }
        }

        misbahaBinding.misbahaReplayBtn.setOnClickListener {
            misbahaViewModel.setMisbahaCount(0)
            misbahaBinding.misbahaBtn.text = "0"
        }

        misbahaBinding.misbahaVibrationBtn.setOnClickListener {
            if (misbahaViewModel.isVibrateOn()) {
                misbahaViewModel.setVibrateOn(false)
                misbahaBinding.misbahaVibrationBtn.setBackgroundResource(R.drawable.vibrate_off)
            } else {
                misbahaViewModel.setVibrateOn(true)
                misbahaBinding.misbahaVibrationBtn.setBackgroundResource(R.drawable.vibrate_on)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        misbahaViewModel.setMisbahaCount(misbahaBinding.misbahaBtn.text.toString().toInt())
    }
}