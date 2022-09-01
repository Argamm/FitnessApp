package com.example.fitnessapp.fragment.navigation.myProfile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentFeedbackBinding
import kotlinx.android.synthetic.main.fragment_feedback.*

class FeedbackFragment : Fragment() {
    lateinit var binding: FragmentFeedbackBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendMessageClickHandler()
    }

    private fun sendMessageClickHandler() {
        binding.btnSendMessage.setOnClickListener {
            val text = edt_feedback.text.toString()
            val intent = Intent()
            val gmail = "mehrabyanargam111@gmail.com"

            if (text.isNotEmpty()) {
                with(intent) {
                    action = Intent.ACTION_SENDTO
                    putExtra(Intent.EXTRA_EMAIL, gmail)
                    putExtra(Intent.EXTRA_SUBJECT, "Feedback")
                    putExtra(Intent.EXTRA_TEXT, text)
                    data = Uri.parse("mailto:${gmail}")
                }
                startActivity(intent)
            }
        }
    }
}