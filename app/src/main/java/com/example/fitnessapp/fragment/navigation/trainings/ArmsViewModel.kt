package com.example.fitnessapp.fragment.navigation.trainings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.RetrofitHelper
import com.example.fitnessapp.api.ApiService
import com.example.fitnessapp.userAniumationDatas.UserTrainingsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArmsViewModel:ViewModel() {
    private val _anilList: MutableStateFlow<ArrayList<UserTrainingsData>?> = MutableStateFlow(null)
    val animList = _anilList.asStateFlow()

    fun getTrainingsAnimation() {
        viewModelScope.launch(Dispatchers.IO) {
            val api = RetrofitHelper.getInstance().create(ApiService::class.java)
            val retrofit = api.getAnimForTrainings()

            withContext(Dispatchers.Main) {
                _anilList.value = retrofit.body()
            }
        }
    }

}