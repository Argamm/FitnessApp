package com.example.fitnessapp.fragment.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.RetrofitHelper
import com.example.fitnessapp.api.ApiService
import com.example.fitnessapp.userAniumationDatas.UserAnimationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private var _list: MutableStateFlow<ArrayList<UserAnimationData>?> = MutableStateFlow(null)
    val list = _list.asStateFlow()

    fun getAnimationsData() {
        viewModelScope.launch(Dispatchers.IO) {
            val api = RetrofitHelper.getInstance().create(ApiService::class.java)
            val retrofit = api.getData()

            withContext(Dispatchers.Main) {
                _list.value = retrofit.body()
            }
        }
    }
}