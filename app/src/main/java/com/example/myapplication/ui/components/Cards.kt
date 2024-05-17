package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.md_theme_light_outline
import com.example.myapplication.ui.theme.md_theme_white


@Composable
fun CardOutlined(
    modifier: Modifier,
    cardPadding: Dp = 16.dp,
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, md_theme_light_outline),
        elevation = 0.dp,
        backgroundColor = md_theme_white
    ) {
        Surface(
            modifier = Modifier.padding(cardPadding),
            content = content
        )
    }

}

@Composable
fun CardElevated(
    modifier: Modifier,
    backgroundColor: Color = md_theme_white,
    onClick: (() -> Unit)? = null,
    cardPadding: Dp = 16.dp,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {

    if (onClick != null) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = modifier,
            elevation = elevation,
            backgroundColor = backgroundColor
        ) {
            Surface(
                modifier = Modifier
                    .clickable { onClick.invoke() }
                    .padding(cardPadding),
                content = content
            )
        }
    } else {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            elevation = elevation,
            backgroundColor = backgroundColor
        ) {
            Surface(
                modifier = Modifier.padding(cardPadding),
                content = content
            )
        }
    }

}
