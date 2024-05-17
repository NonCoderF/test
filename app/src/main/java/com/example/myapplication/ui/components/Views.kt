package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.sqrt

sealed class GridCells {

    abstract val columns: Int

    data class Fixed(val count: Int) : GridCells() {
        override val columns: Int = count
    }

    object Adaptive : GridCells() {
        override val columns: Int = 1
    }
}

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    gridCells: GridCells,
    totalItems: Int,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable (Int) -> Unit,
) {
    Column(modifier = modifier) {
        val columnCount = when (gridCells) {
            is GridCells.Fixed -> gridCells.columns
            is GridCells.Adaptive -> ceil(sqrt(totalItems.toDouble())).toInt()
        }

        val rowCount = ceil(totalItems.toDouble()/columnCount).toInt()

        for(i in 0 until rowCount){
            Row(Modifier.fillMaxWidth()) {
                for (j in 0 until columnCount) {
                    val index = j + i * columnCount
                    Box(
                        Modifier
                            .weight(1f)
                            .padding(contentPadding)
                    ) {
                        if (index < totalItems) {
                            content(index)
                        }
                    }
                }
            }
        }
    }
}