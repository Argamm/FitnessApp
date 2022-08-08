package com.example.myfitnessapp.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.UserInfo
import com.example.myfitnessapp.fragment.navigation.HomeFragment
import com.example.fitnessapp.fragment.navigation.myProfile.MineProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_edit_information.*

const val NAME = "name"
const val AGE = "age"
const val WEIGHT = "weight"
const val HEIGHT = "height"

class EditInformationFragment : Fragment() {
//    private val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//    private val navController = navHostFragment.navController
    lateinit var name: TextView
    lateinit var age: TextView
    lateinit var weight: TextView
    lateinit var height: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_start.isEnabled = false
        name = activity?.findViewById(R.id.edt_name)!!
        age = activity?.findViewById(R.id.edt_age)!!
        weight = activity?.findViewById(R.id.edt_weight)!!
        height = activity?.findViewById(R.id.edt_height)!!



        btn_start.setOnClickListener {

//            if (name.text.isNotEmpty() && age.text.isNotEmpty() && weight.text.isNotEmpty() && height.text.isNotEmpty()) {
//                val mineProfileFragment = MineProfileFragment()
//                val args = Bundle()
//                args.putString(NAME, name.text.toString())
//            args.putString(AGE, age.text.toString())
//                args.putString(WEIGHT, weight.text.toString())
//                args.putString(HEIGHT, height.text.toString())
//
//                mineProfileFragment.arguments = args


//            }
            if (isValidInputs(
                    name.text.toString(),
                    age.text.toString().toInt(),
                    weight.text.toString().toInt(),
                    height.text.toString().toInt()
                )
            ) {
//                val userInfo = UserInfo()
//                userInfo._name = name.text.toString()
//                userInfo._age = age.text.toString()
//                userInfo._weight = weight.text.toString()
//                userInfo._height = height.text.toString()

                (activity as? MainActivity)?.let {
                    it.userInfo._name = name.text.toString()
                    it.userInfo._age = age.text.toString()
                    it.userInfo._weight = weight.text.toString()
                    it.userInfo._height = height.text.toString()
                }

                val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
                navBar?.visibility = VISIBLE

                val homeFragment = HomeFragment()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, homeFragment)?.addToBackStack("homePage")
                    ?.commit()
//                navController.navigate(R.id.action_editInformationFragment_to_homeFragment2)

            }

        }
        edt_name.addTextChangedListener(checkTextWatcher)
        edt_age.addTextChangedListener(checkTextWatcher)
        edt_weight.addTextChangedListener(checkTextWatcher)
        edt_height.addTextChangedListener(checkTextWatcher)
    }

    private val checkTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            btn_start.setTextColor(Color.rgb(173, 173, 173))
            btn_start.setBackgroundResource(R.drawable.btn_background_before)

            if (name.text.isEmpty() || age.text.isEmpty() || weight.text.isEmpty() || height.text.isEmpty()) {
                btn_start.isEnabled = false
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (name.text.isNotEmpty() && age.text.isNotEmpty() && weight.text.isNotEmpty() && height.text.isNotEmpty()) {
                btn_start.isEnabled = true
                btn_start.setTextColor(Color.WHITE)
                btn_start.setBackgroundResource(R.drawable.button_background)

            }
            btn_start.isEnabled =
                name.text.isNotEmpty() && age.text.isNotEmpty() && weight.text.isNotEmpty() && height.text.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun isValidInputs(name: String, age: Int, weight: Int, height: Int): Boolean {
        when {
            name.isEmpty() || !name.first().isUpperCase() -> {
                edt_name.error = "Name must start with Uppercase letter"
                return false
            }
            age <= 9 || age >= 80 -> {
                edt_age.error = "Age must be higher 10 and lower 80"
                return false
            }
            weight <= 49 || weight >= 200 -> {
                edt_weight.error = "Weight must be higher 49 and lower 200"
                return false
            }
            height <= 129 || height >= 260 -> {
                edt_height.error = "Height must be higher 129 and lower 260"
                return false
            }
        }
        return true
    }

}