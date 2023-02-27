package com.michel.galileu.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipeModel(var value: String? = "", var editable: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    companion object : Parceler<RecipeModel> {

        override fun RecipeModel.write(parcel: Parcel, flags: Int) {
            parcel.writeString(value)
            parcel.writeByte(if (editable) 1 else 0)
        }

        override fun create(parcel: Parcel): RecipeModel {
            return RecipeModel(parcel)
        }
    }
}

