package com.vantagefit.core.compose.components

import android.os.Build
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.delay
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R


@Composable
fun HtmlText(
    text: String,
    modifier:
    Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    centerAlign: Boolean = false,
    color: Color? = null,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                setTextColor(context.getColor(R.color.md_theme_text_secondary))
                textSize = textStyle.fontSize.value
                if (centerAlign)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                if (color != null)
                    setTextColor(android.graphics.Color.argb(
                        color.toArgb().alpha,
                        color.toArgb().red,
                        color.toArgb().green,
                        color.toArgb().blue
                    ))
            }
        },
        update = { it.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Composable
fun AutoResizeHtmlText(
    text: String,
    modifier: Modifier = Modifier,
    centerAlign: Boolean = false,
    color: Color? = null,
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            TextView(context).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM)
                }
                setTextColor(context.getColor(R.color.md_theme_text_secondary))
                if (centerAlign)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                if (color != null)
                    setTextColor(android.graphics.Color.argb(
                        color.toArgb().alpha,
                        color.toArgb().red,
                        color.toArgb().green,
                        color.toArgb().blue
                    ))
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            }
        },
        update = {
            it.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    )

}


@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = style.color
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }

    Text(
        modifier = modifier,
        text = text,
        color = color,
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                resizedTextStyle = resizedTextStyle.copy(fontSize = style.fontSize * 0.95)
            } else {
                modifier.drawWithContent { drawContent() }
            }
        }
    )
}


@Composable
fun DrawableWrapper(
    modifier: Modifier = Modifier,
    @DrawableRes drawableTop: Int? = null,
    @DrawableRes drawableStart: Int? = null,
    @DrawableRes drawableBottom: Int? = null,
    @DrawableRes drawableEnd: Int? = null,
    drawableAlpha: Float = 1F,
    content: @Composable () -> Unit,
) {
    ConstraintLayout(modifier) {
        val (refImgStart, refImgTop, refImgBottom, refImgEnd, refContent) = createRefs()
        Box(Modifier.constrainAs(refContent) {
            top.linkTo(drawableTop?.let { refImgTop.bottom } ?: parent.top)
            bottom.linkTo(drawableBottom?.let { refImgBottom.top } ?: parent.bottom)
            start.linkTo(drawableStart?.let { refImgStart.end } ?: parent.start)
            end.linkTo(drawableEnd?.let { refImgEnd.start } ?: parent.end)
        }) {
            content()
        }
        drawableTop?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier
                    .constrainAs(refImgTop) {
                        top.linkTo(parent.top)
                        start.linkTo(refContent.start)
                        end.linkTo(refContent.end)
                    }
                    .alpha(alpha = drawableAlpha)
            )
        }
        drawableStart?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier
                    .constrainAs(refImgStart) {
                        top.linkTo(refContent.top)
                        bottom.linkTo(refContent.bottom)
                        start.linkTo(parent.start)
                    }
                    .alpha(alpha = drawableAlpha)
            )
        }
        drawableBottom?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier
                    .constrainAs(refImgBottom) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(refContent.start)
                        end.linkTo(refContent.end)
                    }
                    .alpha(alpha = drawableAlpha)
            )
        }
        drawableEnd?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier
                    .constrainAs(refImgEnd) {
                        top.linkTo(refContent.top)
                        bottom.linkTo(refContent.bottom)
                        end.linkTo(parent.end)
                    }
                    .alpha(alpha = drawableAlpha)
            )
        }
    }
}

@Composable
fun MarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    gradientEdgeColor: Color = Color.Transparent,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val createText = @Composable { localModifier: Modifier ->
        Text(
            text,
            textAlign = textAlign,
            modifier = localModifier,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = 1,
            onTextLayout = onTextLayout,
            style = style,
        )
    }
    var offset by remember { mutableStateOf(0) }
    val textLayoutInfoState = remember { mutableStateOf<TextLayoutInfo?>(null) }
    LaunchedEffect(textLayoutInfoState.value) {
        val textLayoutInfo = textLayoutInfoState.value ?: return@LaunchedEffect
        if (textLayoutInfo.textWidth <= textLayoutInfo.containerWidth) return@LaunchedEffect
        val duration = 7500 * textLayoutInfo.textWidth / textLayoutInfo.containerWidth
        val delay = 1000L

        do {
            val animation = TargetBasedAnimation(
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        delayMillis = 1000,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                typeConverter = Int.VectorConverter,
                initialValue = 0,
                targetValue = -textLayoutInfo.textWidth
            )
            val startTime = withFrameNanos { it }
            do {
                val playTime = withFrameNanos { it } - startTime
                offset = (animation.getValueFromNanos(playTime))
            } while (!animation.isFinishedFromNanos(playTime))
            delay(delay)
        } while (true)
    }

    SubcomposeLayout(
        modifier = modifier.clipToBounds()
    ) { constraints ->
        val infiniteWidthConstraints = constraints.copy(maxWidth = Int.MAX_VALUE)
        var mainText = subcompose(MarqueeLayers.MainText) {
            createText(textModifier)
        }.first().measure(infiniteWidthConstraints)

        var gradient: Placeable? = null

        var secondPlaceableWithOffset: Pair<Placeable, Int>? = null
        if (mainText.width <= constraints.maxWidth) {
            mainText = subcompose(MarqueeLayers.SecondaryText) {
                createText(textModifier.fillMaxWidth())
            }.first().measure(constraints)
            textLayoutInfoState.value = null
        } else {
            val spacing = constraints.maxWidth * 2 / 3
            textLayoutInfoState.value = TextLayoutInfo(
                textWidth = mainText.width + spacing,
                containerWidth = constraints.maxWidth
            )
            val secondTextOffset = mainText.width + offset + spacing
            val secondTextSpace = constraints.maxWidth - secondTextOffset
            if (secondTextSpace > 0) {
                secondPlaceableWithOffset = subcompose(MarqueeLayers.SecondaryText) {
                    createText(textModifier)
                }.first().measure(infiniteWidthConstraints) to secondTextOffset
            }
            gradient = subcompose(MarqueeLayers.EdgesGradient) {
                Row {
                    GradientEdge(gradientEdgeColor, Color.Transparent)
                    Spacer(Modifier.weight(1f))
                    GradientEdge(Color.Transparent, gradientEdgeColor)
                }
            }.first().measure(constraints.copy(maxHeight = mainText.height))
        }

        layout(
            width = constraints.maxWidth,
            height = mainText.height
        ) {
            mainText.place(offset, 0)
            secondPlaceableWithOffset?.let {
                it.first.place(it.second, 0)
            }
            gradient?.place(0, 0)
        }
    }
}

@Composable
private fun GradientEdge(
    startColor: Color, endColor: Color,
) {
    Box(
        modifier = Modifier
            .width(10.dp)
            .fillMaxHeight()
            .background(
                brush = Brush.horizontalGradient(
                    0f to startColor, 1f to endColor,
                )
            )
    )
}

private enum class MarqueeLayers { MainText, SecondaryText, EdgesGradient }
private data class TextLayoutInfo(val textWidth: Int, val containerWidth: Int)

@Composable
fun MultiStyleText(
    modifier : Modifier = Modifier,
    text1: String,
    text2: String,
    style1 : TextStyle,
    style2 : TextStyle,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
        withStyle(style = SpanStyle(
            color = style1.color,
            fontStyle = style1.fontStyle,
            fontWeight = style1.fontWeight,
            fontFamily = style1.fontFamily
        )) {
            append(text1)
        }
        withStyle(style = SpanStyle(
            color = style2.color,
            fontStyle = style2.fontStyle,
            fontWeight = style2.fontWeight,
            fontFamily = style2.fontFamily
        )) {
            append(text2)
        }
    })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    content: @Composable() (count : Int) -> Unit = {}
) {
    AnimatedContent(
        modifier = modifier,
        targetState = count,
        transitionSpec = {
            slideInVertically(
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                )
            ) { it } with slideOutVertically(
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                )
            ) { -it }
        }
    ) { char ->
        content(char.toString().toInt())
    }
}