package com.example.fitnessapp.fragment.navigation.myProfile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentCommunicateBinding

class CommunicateFragment : Fragment() {
    lateinit var binding: FragmentCommunicateBinding
    lateinit var intent: Intent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunicateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnFacebook.setOnClickListener {
                intent = try {
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/profile.php?id=100074110905331")
                    )
                } catch (e: Exception) {
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"))
                }
                startActivity(intent)
            }
            btnInstagram.setOnClickListener {
                intent = try {
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/photosbyarg"))
                } catch (e: Exception) {
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"))
                }
                startActivity(intent)
            }
            btnWhatsapp.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + Uri.encode("+37498269334".trim()))
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(callIntent)
            }

            btnGmail.setOnClickListener {
                intent = Intent(Intent.ACTION_VIEW)
                val data = Uri.parse("mailto:" + "mehrabyanargam111@gmail.com")
                intent.data = data
                startActivity(intent)
            }
        }
    }
}