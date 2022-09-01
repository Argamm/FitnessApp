package com.example.fitnessapp.api

import com.example.fitnessapp.userAniumationDatas.UserAnimationData
import com.example.fitnessapp.userAniumationDatas.UserTrainingsData
import com.example.fitnessapp.userAniumationDatas.UserTrainingsList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("FMYN")
    suspend fun getData(): Response<ArrayList<UserAnimationData>?>

    @GET("E6JW")
    suspend fun getAnimForTrainings(): Response<UserTrainingsList>
}