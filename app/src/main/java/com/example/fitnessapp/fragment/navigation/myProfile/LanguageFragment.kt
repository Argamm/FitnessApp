package com.example.fitnessapp.fragment.navigation.myProfile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.*
import com.example.fitnessapp.databinding.FragmentLanguageBinding


class LanguageFragment : Fragment() {
    lateinit var binding: FragmentLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val language = sharedPreference.getString(LANG, "")

        setLocale((activity as MainActivity), language)

        when (language) {
            "hy" -> binding.radioBtnArmenian.isChecked = true
            "ru" -> binding.radioBtnRussian.isChecked = true
            "en" -> binding.radioBtnEnglish.isChecked = true
        }

        var checkLanguageStr = ""

        binding.radioBtnRussian.setOnCheckedChangeListener { _, b ->
            if (b) {
                checkLanguageStr = "ru"
            }
        }
        binding.radioBtnArmenian.setOnCheckedChangeListener { _, b ->
            if (b) {
                checkLanguageStr = "hy"
            }
        }
        binding.radioBtnEnglish.setOnCheckedChangeListener { _, b ->
            if (b) {
                checkLanguageStr = "en"
            }
        }

        binding.btnSetLanguage.setOnClickListener {
            sharedPreference.edit().putBoolean(LANGUAGE_MODE, true).apply()
            sharedPreference.edit().putString(LANG, checkLanguageStr).apply()

            Toast.makeText(context, checkLanguageStr, Toast.LENGTH_SHORT).show()
            (activity as MainActivity).recreate()
            setLocale((activity as MainActivity), checkLanguageStr)
        }
    }
}