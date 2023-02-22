package com.michel.galileu.ui.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeCard(
    recipeData: ItemList,
    onPressCheckBox: () -> Unit,
    isSelected: () -> Boolean,
    isSelectedValues: () -> Boolean,
    onRecipeDetailsClick: (Int) -> Unit
) {

    Row() {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected()) Color.Blue else MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(75.dp)
                .combinedClickable(onClick = {
                    if (isSelectedValues()) {
                        onPressCheckBox()
                    } else {
                        onRecipeDetailsClick(recipeData.value.id)
                    }
                }, onLongClick = {
                    onPressCheckBox();
                })

        ) {

            Row(
                modifier = Modifier.padding(all = 4.dp)

            ) {
                Column(modifier = Modifier.padding(all = 4.dp)) {
                    Text(
                        text = recipeData.value.title, color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    recipeData.value.subtitle?.let { Text(text = it) }
                }
            }
        }
    }

}
