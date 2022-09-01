package com.example.fitnessapp.userAniumationDatas

import com.google.gson.annotations.SerializedName

data class UserTrainingsList(
    @SerializedName("data")
    val data: ArrayList<UserTrainingsData>
)
