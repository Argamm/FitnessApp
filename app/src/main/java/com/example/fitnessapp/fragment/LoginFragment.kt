package com.example.fitnessapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.*
import com.example.fitnessapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {
    lateinit var sharedPreference: SharedPreferences
    lateinit var navController: NavController
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        val toolBar = activity?.findViewById<Toolbar>(R.id.myToolbar)
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
        toolBar?.visibility = View.GONE
        bottomNav?.visibility = View.GONE

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            btnLogin.isEnabled = false
            btnLogin.setOnClickListener {
                if (!isValidEmail(edtEmail.text.toString()))
                    Toast.makeText(context, getString(R.string.wrongEmail), Toast.LENGTH_SHORT)
                        .show()
                else if (!isValidPassword(binding.edtPassword.text.toString()))
                    Toast.makeText(context, getString(R.string.WrongPassword), Toast.LENGTH_SHORT)
                        .show()
                else {
                    if (edtEmail.text.toString() == sharedPreference.getString(EMAIL_STR, "")
                        && edtPassword.text.toString() == sharedPreference.getString(
                            PASSWORD_STR,
                            ""
                        )
                    ) {
                        toolBar?.visibility = View.VISIBLE
                        bottomNav?.visibility = View.VISIBLE
                        navController.navigate(R.id.action_loginFragment_to_homeFragment2)
                    } else {
                        Toast.makeText(context, getString(R.string.WrongInputs), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            tvSingUp.setOnClickListener {
                navController.navigate(R.id.action_loginFragment_to_selectGenderFragment)
            }
            edtEmail.addTextChangedListener(loginTextWatcher)
            edtPassword.addTextChangedListener(loginTextWatcher)
        }
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val emailInput = binding.edtEmail.text.toString().trim()
            val passwordInput = binding.edtPassword.text.toString().trim()

            if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                binding.btnLogin.setBackgroundColor(Color.rgb(211, 211, 211))
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            with(binding) {
                val emailInput = edtEmail.text.toString().trim()
                val passwordInput = edtPassword.text.toString().trim()

                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                    btnLogin.isEnabled = true
                    btnLogin.setBackgroundColor(Color.WHITE)
                }
                btnLogin.isEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty()
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}

