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
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.regex.Matcher
import java.util.regex.Pattern

const val PREFERENCE_NAME = "PREFERENCE_NAME"
const val BOOLEAN_CHECK = "BOOLEAN_CHECK"
const val PASSWORD_STR = "PASSWORD_STR"
const val EMAIL_STR = "EMAIL_STR"

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

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            btnLogin.isEnabled = false

            btnLogin.setOnClickListener {
                if (!isValidEmail(edtEmail.text.toString()))
                    Toast.makeText(context, "wrong email", Toast.LENGTH_SHORT).show()
                else if (!isValidPassword(binding.edtPassword.text.toString()))
                    Toast.makeText(context, "wrong password", Toast.LENGTH_SHORT).show()
                else {
                    val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
                    val myToolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.myToolbar)

                    if (edtEmail.text.toString() == sharedPreference.getString(EMAIL_STR, "")
                        && edtPassword.text.toString() == sharedPreference.getString(PASSWORD_STR, "")
                    ) {
                        navBar?.visibility = View.VISIBLE
                        myToolbar?.visibility = View.VISIBLE
                        navController.navigate(R.id.action_loginFragment_to_homeFragment2)
                    } else {
                        Toast.makeText(context, "Wrong inputs", Toast.LENGTH_SHORT).show()
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
            val emailInput = binding.edtEmail.text.toString().trim()
            val passwordInput = binding.edtPassword.text.toString().trim()

            if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                binding.btnLogin.isEnabled = true
                binding.btnLogin.setBackgroundColor(Color.WHITE)

            }
            binding.btnLogin.isEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }


//    private fun isValidEmail(email: String): Boolean {
////        if ((email.contains("mail.ru") && email.length >= 8) || (email.contains("gmail.com") && email.length >= 11))
////            return true
//        val pattern: Pattern
//        val emailPattern =
//            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
//        pattern = Pattern.compile(emailPattern)
//        val matcher: Matcher = pattern.matcher(email.toString())
//        return matcher.matches()
//
//    }
//
//
//    private fun isValidPassword(password: String): Boolean {
//        if (password.length < 8 || password == "12345678" || password == "00000000" || password == "87654321")
//            return false
//        if (password.firstOrNull { it.isDigit() } == null) return false
//        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
//        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
//        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false
//
//        return true
//    }

}

fun isValidPassword(password: String): Boolean {
    if (password.length < 8 || password == "12345678" || password == "00000000" || password == "87654321")
        return false
    if (password.firstOrNull { it.isDigit() } == null) return false
    if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
    if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
    if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

    return true
}

fun isValidEmail(email: String): Boolean {
//        if ((email.contains("mail.ru") && email.length >= 8) || (email.contains("gmail.com") && email.length >= 11))
//            return true
    val pattern: Pattern
    val emailPattern =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    pattern = Pattern.compile(emailPattern)
    val matcher: Matcher = pattern.matcher(email.toString())
    return matcher.matches()

}