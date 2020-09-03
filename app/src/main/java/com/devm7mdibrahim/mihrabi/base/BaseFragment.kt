package com.devm7mdibrahim.mihrabi.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.devm7mdibrahim.mihrabi.utils.Constants

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            getFragmentView(),
            container,
            false
        )
        return binding?.root
    }

    abstract fun getFragmentView(): Int

    protected fun showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    protected fun displayLog(message: String) =
        Log.d(Constants.TAG, message)

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}