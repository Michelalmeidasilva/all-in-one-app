package com.michel.galileu.utils

import com.michel.galileu.ui.screens.recipe.ListType

fun changeListType(typeList: ListType, index: Int): String {
    return when (typeList) {
        ListType.BULLET_LIST -> "·"
        ListType.NUMBERED_LIST -> "$index."
        ListType.ORDERED_LIST -> "-"
    }
}