package com.michel.galileu.data.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class ItemForm(var value: String? = "", var editable: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    companion object : Parceler<ItemForm> {

        override fun ItemForm.write(parcel: Parcel, flags: Int) {
            parcel.writeString(value)
            parcel.writeByte(if (editable) 1 else 0)
        }

        override fun create(parcel: Parcel): ItemForm {
            return ItemForm(parcel)
        }
    }
}

