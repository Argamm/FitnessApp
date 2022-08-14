package com.example.fitnessapp.fragment

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
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentEditInformationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_edit_information.*

const val NAME = "name"
const val AGE = "age"
const val WEIGHT = "weight"
const val HEIGHT = "height"

class EditInformationFragment : Fragment() {
    lateinit var binding: FragmentEditInformationBinding
    lateinit var navController: NavController

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
        binding.btnStart.isEnabled = false

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        buttonClickHandler()

        binding.edtName.addTextChangedListener(checkTextWatcher)
        binding.edtAge.addTextChangedListener(checkTextWatcher)
        binding.edtWeight.addTextChangedListener(checkTextWatcher)
        binding.edtHeight.addTextChangedListener(checkTextWatcher)
    }

    fun buttonClickHandler() {
        binding.btnStart.setOnClickListener {
            if (isValidInputs(
                    binding.edtName.text.toString(),
                    binding.edtAge.text.toString().toInt(),
                    binding.edtWeight.text.toString().toInt(),
                    binding.edtHeight.text.toString().toInt()
                )
            ) {

                (activity as? MainActivity)?.let {
                    it.userInfo.userName = binding.edtName.text.toString()
                    it.userInfo.userAge = binding.edtAge.text.toString()
                    it.userInfo.userWeight = binding.edtWeight.text.toString()
                    it.userInfo.userHeight = binding.edtHeight.text.toString()
                }

                val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
                navBar?.visibility = VISIBLE
                navController.navigate(R.id.action_editInformationFragment_to_homeFragment2)
            }
        }
    }

    private val checkTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            btn_start.setTextColor(Color.rgb(173, 173, 173))
            btn_start.setBackgroundResource(R.drawable.btn_background_before)

            if (binding.edtName.text.isNullOrEmpty() || binding.edtAge.text.isNullOrEmpty() || binding.edtWeight.text.isNullOrEmpty() || binding.edtHeight.text.isNullOrEmpty()) {
                binding.btnStart.isEnabled = false
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.edtName.text?.isNotEmpty() == true
                && binding.edtAge.text?.isNotEmpty() == true
                && binding.edtWeight.text?.isNotEmpty() == true
                && binding.edtHeight.text?.isNotEmpty() == true
            ) {
                binding.btnStart.isEnabled = true
                binding.btnStart.setTextColor(Color.WHITE)
                binding.btnStart.setBackgroundResource(R.drawable.button_background)

            }
            binding.btnStart.isEnabled =
                binding.edtName.text?.isNotEmpty() == true
                        && binding.edtAge.text?.isNotEmpty() == true
                        && binding.edtWeight.text?.isNotEmpty() == true
                        && binding.edtHeight.text?.isNotEmpty() == true
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun isValidInputs(name: String, age: Int, weight: Int, height: Int): Boolean {
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
        }
        return true
    }
}