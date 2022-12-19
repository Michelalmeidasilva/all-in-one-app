package com.michel.galileu.models.recipe

import android.os.Parcel
import android.os.Parcelable

data class RecipeModel(val steps: Array<String>, val subtitle: String, val title: String, val ingredients: Array<String>):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArray() as Array<String>,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArray() as Array<String>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(steps)
        parcel.writeString(subtitle)
        parcel.writeString(title)
        parcel.writeStringArray(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeModel> {
        override fun createFromParcel(parcel: Parcel): RecipeModel {
            return RecipeModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeModel?> {
            return arrayOfNulls(size)
        }
    }
}