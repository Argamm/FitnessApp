package com.example.fitnessapp.fragment.navigation.customTrainings

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Tasks(val taskLabel: String, val taskDescription: String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parsel: Parcel?, p1: Int) {
        parsel?.writeString(taskLabel)
        parsel?.writeString(taskDescription)
    }

    companion object CREATOR : Parcelable.Creator<Tasks> {
        override fun createFromParcel(parcel: Parcel): Tasks = Tasks(parcel)

        override fun newArray(size: Int): Array<Tasks?> = arrayOfNulls(size)
    }
}