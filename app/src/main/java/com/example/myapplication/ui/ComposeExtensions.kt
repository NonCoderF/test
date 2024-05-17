package com.example.myapplication.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ColumnScope.shimmerBrush(): Brush {

    val ShimmerColors = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutLinearInEasing),
            RepeatMode.Reverse
        )
    )

    val startOffset = Offset(10f, 10f)
    val endOffset = Offset(translateAnim.value, translateAnim.value)

    return if(startOffset != Offset.Unspecified && endOffset != Offset.Unspecified)
        Brush.linearGradient(
            colors = ShimmerColors,
            start = startOffset,
            end = endOffset
        )
    else Brush.linearGradient(
        colors = ShimmerColors,
    )
}

@Composable
fun RowScope.shimmerBrush(): Brush {

    val ShimmerColors = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutLinearInEasing),
            RepeatMode.Reverse
        )
    )

    val startOffset = Offset(10f, 10f)
    val endOffset = Offset(translateAnim.value, translateAnim.value)

    return if(startOffset != Offset.Unspecified && endOffset != Offset.Unspecified)
        Brush.linearGradient(
            colors = ShimmerColors,
            start = startOffset,
            end = endOffset
        )
    else Brush.linearGradient(
        colors = ShimmerColors,
    )
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.ifThen(condition: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        block(this)
    } else {
        this
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

