package com.example.myfitnessapp.fragment.navigation.myProfile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_feedback.*

class FeedbackFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_send_message.setOnClickListener {
            val text = edt_feedback.text.toString()
            val intent = Intent()
            val gmail = "mehrabyanargam111@gmail.com"


            if (text.isNotEmpty()) {
                intent.action = Intent.ACTION_SENDTO
                intent.putExtra(Intent.EXTRA_EMAIL, gmail)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
                intent.putExtra(Intent.EXTRA_TEXT, text)
//            intent.type = "message/rfc822"
                intent.data = Uri.parse("mailto:${gmail}")
                startActivity(intent)
            }

        }
    }
}