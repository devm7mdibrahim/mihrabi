package com.devm7mdibrahim.mihrabi.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FragmentMainBinding
import com.devm7mdibrahim.mihrabi.utils.Constants

class MainFragment : Fragment() {

    private lateinit var bundle: Bundle
    private lateinit var fragmentMainBinding: FragmentMainBinding

    private val navController: NavController by lazy {
        view?.let { Navigation.findNavController(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainBinding.prayerTimesButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_prayerTimesFragment) }
        fragmentMainBinding.azkarButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_azkarFragment) }
        fragmentMainBinding.nearbyMosquesButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_nearMosquesFragment) }
        fragmentMainBinding.quranButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_surahListFragment) }
        fragmentMainBinding.awlMaraAsalyButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_awlMaraAsalyFragment) }
        fragmentMainBinding.imaniatButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_imaniatFragment) }
        fragmentMainBinding.qiblaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_qiblaFragment) }
        fragmentMainBinding.qadaaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_qadaaFragment) }
        fragmentMainBinding.fadlElsalahButton.setOnClickListener { navController.navigate(R.id.action_main_fragment_to_fadlElsalahFragment) }
        fragmentMainBinding.misbahaButton.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_misbahaFragment) }

        fragmentMainBinding.fiqhElsalahButton.setOnClickListener {
            bundle = bundleOf(
                Constants.FIQH_TYPE to 1,
                Constants.FIQH_NAME to fragmentMainBinding.fiqhElsalahButton.text
            )
            navController.navigate(R.id.action_mainFragment_to_fiqhFragment, bundle)
        }
        fragmentMainBinding.fiqhEltaharaButton.setOnClickListener {
            bundle = bundleOf(
                Constants.FIQH_TYPE to 2,
                Constants.FIQH_NAME to fragmentMainBinding.fiqhEltaharaButton.text
            )
            navController.navigate(R.id.action_mainFragment_to_fiqhFragment, bundle)
        }
    }
}
