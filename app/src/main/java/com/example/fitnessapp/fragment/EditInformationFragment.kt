package com.example.fitnessapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentEditInformationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_edit_information.*

const val NAME = "name"
const val AGE = "age"
const val WEIGHT = "weight"
const val HEIGHT = "height"
const val EDT_NAME = "EDT_NAME"
const val EDT_AGE = "EDT_AGE"
const val EDT_WEIGHT = "EDT_WEIGHT"
const val EDT_HEIGHT = "EDT_HEIGHT"
const val EDT_GENDER = "EDT_GENDER"
const val EDT_EMAIL = "EDT_EMAIL"
const val EDT_TARGET_WEIGHT = "EDT_TARGET_WEIGHT"


class EditInformationFragment : Fragment() {
    lateinit var binding: FragmentEditInformationBinding
    lateinit var navController: NavController
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        binding.btnStart.isEnabled = false

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        buttonClickHandler()

        binding.edtName.addTextChangedListener(checkTextWatcher)
        binding.edtAge.addTextChangedListener(checkTextWatcher)
        binding.edtWeight.addTextChangedListener(checkTextWatcher)
        binding.edtHeight.addTextChangedListener(checkTextWatcher)
        binding.edtEmailRegister.addTextChangedListener(checkTextWatcher)
        binding.edtPasswordRegister.addTextChangedListener(checkTextWatcher)
    }

    fun buttonClickHandler() {
        binding.btnStart.setOnClickListener {
            if (isValidInputs(
                    binding.edtName.text.toString(),
                    binding.edtAge.text.toString().toInt(),
                    binding.edtWeight.text.toString().toInt(),
                    binding.edtHeight.text.toString().toInt(),
                    binding.edtEmailRegister.text.toString(),
                    binding.edtPasswordRegister.text.toString()
                )
            ) {
                val myToolbar = activity?.findViewById<Toolbar>(R.id.myToolbar)
                myToolbar?.visibility = VISIBLE

                sharedPreferences.edit()
                    .putString(PASSWORD_STR, binding.edtPasswordRegister.text.toString())
                    .apply()
                sharedPreferences.edit().putString(EDT_NAME, binding.edtName.text.toString())
                    .apply()
                sharedPreferences.edit()
                    .putString(EDT_WEIGHT, binding.edtWeight.text.toString()).apply()
                sharedPreferences.edit()
                    .putString(EDT_HEIGHT, binding.edtHeight.text.toString()).apply()
                sharedPreferences.edit().putString(EDT_AGE, binding.edtAge.text.toString())
                    .apply()
                sharedPreferences.edit()
                    .putString(EDT_EMAIL, binding.edtEmailRegister.text.toString()).apply()
            }

            val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
            navBar?.visibility = VISIBLE
            navController.navigate(R.id.action_editInformationFragment_to_homeFragment2)

        }
    }

    //    private fun sharedPrefSendInfo(edtPassword: String, edtEmail: String) {
//
////        sharedPreferences.edit().putBoolean(BOOLEAN_CHECK, true).apply()
//        sharedPreferences.edit().putString(EMAIL_STR, edtEmail).apply()
//
//    }
    private val checkTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            btn_start.setTextColor(Color.rgb(173, 173, 173))
            btn_start.setBackgroundResource(R.drawable.btn_background_before)

            if (binding.edtName.text.isNullOrEmpty()
                || binding.edtAge.text.isNullOrEmpty()
                || binding.edtEmailRegister.text.isNullOrEmpty()
                || binding.edtPasswordRegister.text.isNullOrEmpty()
                || binding.edtWeight.text.isNullOrEmpty()
                || binding.edtHeight.text.isNullOrEmpty()
            ) {
                binding.btnStart.isEnabled = false
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if ((binding.edtName.text?.isNotEmpty() == true)
                && (binding.edtEmailRegister.text?.isNotEmpty() == true)
                && (binding.edtAge.text?.isNotEmpty() == true)
                && (binding.edtWeight.text?.isNotEmpty() == true)
                && (binding.edtHeight.text?.isNotEmpty() == true)
                && (binding.edtPasswordRegister.text?.isNotEmpty() == true)

            ) {
                binding.btnStart.isEnabled = true
                binding.btnStart.setTextColor(Color.WHITE)
                binding.btnStart.setBackgroundResource(R.drawable.button_background)

            }
            binding.btnStart.isEnabled =
                binding.edtName.text?.isNotEmpty() == true
                        && binding.edtEmailRegister.text?.isNotEmpty() == true
                        && binding.edtPasswordRegister.text?.isNotEmpty() == true
                        && binding.edtAge.text?.isNotEmpty() == true
                        && binding.edtWeight.text?.isNotEmpty() == true
                        && binding.edtHeight.text?.isNotEmpty() == true
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun isValidInputs(
        name: String,
        age: Int,
        weight: Int,
        height: Int,
        email: String,
        password: String
    ): Boolean {
        when {
            name.isEmpty() || !name.first().isUpperCase() -> {
                binding.edtName.error = "Name must start with Uppercase letter"
                return false
            }
            age <= 9 || age >= 80 -> {
                binding.edtAge.error = "Age must be higher 10 and lower 80"
                return false
            }
            weight <= 49 || weight >= 200 -> {
                binding.edtWeight.error = "Weight must be higher 49 and lower 200"
                return false
            }
            height <= 129 || height >= 260 -> {
                binding.edtHeight.error = "Height must be higher 129 and lower 260"
                return false
            }
            !isValidEmail(email) -> {
                binding.edtEmailRegister.error =
                    "Wrong Email format, try again with right one!"
                return false

            }
            !isValidPassword(password) -> {
                binding.edtPasswordRegister.error =
                    "Password must be longer than 8 sym., contain one uppercase, one lowercase, one letter and one sym."
                return false
            }
        }
        return true
    }
}