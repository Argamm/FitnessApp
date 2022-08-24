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
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.*
import com.example.fitnessapp.databinding.FragmentEditInformationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_edit_information.*


class EditInformationFragment : Fragment() {
    lateinit var binding: FragmentEditInformationBinding
    lateinit var navController: NavController
    lateinit var sharedPreferences: SharedPreferences
    lateinit var toolBar: androidx.appcompat.widget.Toolbar
    lateinit var bottomNav: BottomNavigationView

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

        toolBar = activity?.findViewById(R.id.myToolbar)!!
        bottomNav = activity?.findViewById(R.id.bottom_nav)!!

        toolBar.visibility = View.GONE
        bottomNav.visibility = View.GONE

        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        binding.btnStart.isEnabled = false

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        buttonClickHandler()
        with(binding) {
            edtName.addTextChangedListener(checkTextWatcher)
            edtAge.addTextChangedListener(checkTextWatcher)
            edtWeight.addTextChangedListener(checkTextWatcher)
            edtHeight.addTextChangedListener(checkTextWatcher)
            edtEmailRegister.addTextChangedListener(checkTextWatcher)
            edtPasswordRegister.addTextChangedListener(checkTextWatcher)
        }
    }

    fun buttonClickHandler() {
        with(binding) {

            btnStart.setOnClickListener {
                if (isValidInputs(
                        edtName.text.toString(),
                        edtAge.text.toString().toInt(),
                        edtWeight.text.toString().toInt(),
                        edtHeight.text.toString().toInt(),
                        edtEmailRegister.text.toString(),
                        edtPasswordRegister.text.toString()
                    )
                ) {

                    sharedPreferences.edit()
                        .putString(PASSWORD_STR, edtPasswordRegister.text.toString())
                        .apply()
                    sharedPreferences.edit().putString(EDT_NAME, edtName.text.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString(EDT_WEIGHT, edtWeight.text.toString()).apply()
                    sharedPreferences.edit()
                        .putString(EDT_HEIGHT, edtHeight.text.toString()).apply()
                    sharedPreferences.edit().putString(EDT_AGE, edtAge.text.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString(EDT_EMAIL, edtEmailRegister.text.toString()).apply()
                }

                toolBar.visibility = VISIBLE
                bottomNav.visibility = VISIBLE
                navController.navigate(R.id.action_editInformationFragment_to_homeFragment2)

            }
        }
    }

    private val checkTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            btn_start.setTextColor(Color.rgb(173, 173, 173))
            btn_start.setBackgroundResource(R.drawable.btn_background_before)
            with(binding) {
                if (edtName.text.isNullOrEmpty()
                    || edtAge.text.isNullOrEmpty()
                    || edtEmailRegister.text.isNullOrEmpty()
                    || edtPasswordRegister.text.isNullOrEmpty()
                    || edtWeight.text.isNullOrEmpty()
                    || edtHeight.text.isNullOrEmpty()
                ) {
                    btnStart.isEnabled = false
                }
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            with(binding) {
                if ((edtName.text?.isNotEmpty() == true)
                    && (edtEmailRegister.text?.isNotEmpty() == true)
                    && (edtAge.text?.isNotEmpty() == true)
                    && (edtWeight.text?.isNotEmpty() == true)
                    && (edtHeight.text?.isNotEmpty() == true)
                    && (edtPasswordRegister.text?.isNotEmpty() == true)

                ) {
                    btnStart.isEnabled = true
                    btnStart.setTextColor(Color.WHITE)
                    btnStart.setBackgroundResource(R.drawable.button_background)

                }
                btnStart.isEnabled =
                    edtName.text?.isNotEmpty() == true
                            && edtEmailRegister.text?.isNotEmpty() == true
                            && edtPasswordRegister.text?.isNotEmpty() == true
                            && edtAge.text?.isNotEmpty() == true
                            && edtWeight.text?.isNotEmpty() == true
                            && edtHeight.text?.isNotEmpty() == true
            }
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
                binding.edtName.error = getString(R.string.withUppercase)
                return false
            }
            age <= 9 || age >= 80 -> {
                binding.edtAge.error = getString(R.string.ageSize)
                return false
            }
            weight <= 49 || weight >= 200 -> {
                binding.edtWeight.error = getString(R.string.weightSize)
                return false
            }
            height <= 129 || height >= 260 -> {
                binding.edtHeight.error = getString(R.string.heightSize)
                return false
            }
            !isValidEmail(email) -> {
                binding.edtEmailRegister.error =
                    getString(R.string.wrongEmail_)
                return false

            }
            !isValidPassword(password) -> {
                binding.edtPasswordRegister.error =
                    getString(R.string.passwordWrong)
                return false
            }
        }
        return true
    }
}