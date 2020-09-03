package com.devm7mdibrahim.mihrabi.ui.communication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devm7mdibrahim.mihrabi.databinding.FragmentCommunicationBinding

class CommunicationFragment : Fragment() {

    private lateinit var communicationBinding: FragmentCommunicationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicationBinding = FragmentCommunicationBinding.inflate(inflater, container, false)
        communicationBinding.lifecycleOwner = this
        return communicationBinding.root
    }

    override fun onResume() {
        super.onResume()

        communicationBinding.gmailIv.setOnClickListener {
            openEmailApp()
        }
        communicationBinding.facebookIv.setOnClickListener {
            openSocialURL("https://www.facebook.com/devM7mdibrahim/")
        }
        communicationBinding.linkedinIv.setOnClickListener {
            openSocialURL("https://www.linkedin.com/in/devm7mdibrahim/")
        }
        communicationBinding.telegramIv.setOnClickListener {
            openSocialURL("https://t.me/devM7mdibrahim")
        }
    }

    private fun openSocialURL(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)


    }

    private fun openEmailApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("dev.m7mdibrahim@gmail.com"))
        startActivity(Intent.createChooser(intent, "Send mail..."))
    }
}