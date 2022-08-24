package com.example.fitnessapp

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun setLocale(activity: Activity, languageCode: String?) {
    val locale = languageCode?.let { Locale(it) }
    if (locale != null) {
        Locale.setDefault(locale)
    }
    val resources: Resources = activity.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun View.hideKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
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