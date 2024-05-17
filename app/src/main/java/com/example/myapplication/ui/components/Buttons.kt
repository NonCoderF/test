package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.ifThen
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.md_drawable_padding
import com.example.myapplication.ui.theme.md_layout_margin
import com.example.myapplication.ui.theme.md_text_margin
import com.example.myapplication.ui.theme.md_theme_light_primary
import com.example.myapplication.ui.theme.md_theme_light_primaryContainer
import com.example.myapplication.ui.theme.md_theme_text_primary
import java.util.Locale

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    contentPadding: Dp = 16.dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier.padding(contentPadding)
    ) {
        Text(
            text = text,
            style = typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = text,
            style = typography.titleMedium
        )
    }
}

@Composable
fun TextButtonPrimary(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    contentPadding: Dp = 16.dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier.padding(contentPadding)
    ) {
        Text(
            text = text,
            style = typography.titleMedium
        )
    }
}

@Composable
fun ButtonOutlinedBlack(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(50),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = text,
        )
    }
}

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    color: Color = Color.Black,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        onClick = onClick,
        border = BorderStroke(1.dp, color),
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ButtonHalfFilled(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = md_theme_light_primaryContainer
        ),
        shape = RoundedCornerShape(50),
        onClick = onClick,
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = text,
            color = md_theme_text_primary
        )
    }
}

@Composable
fun RoundedButtonRed(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
) {

    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xfff15162),
            Color(0xfff15162),
            Color(0xfff8654a)
        )
    )

    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        ),
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .background(brush = horizontalGradientBrush),
        onClick = onClick
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = text.uppercase(),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun RoundedButton(
    text: String = "",
    textColor: Color = Color.White,
    onClick: () -> Unit = {},
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor
        ),
        shape = RoundedCornerShape(50),
        onClick = onClick,
        modifier = Modifier.height(50.dp)
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = text.uppercase(),
            modifier = Modifier.padding(horizontal = 20.dp),
        )
    }
}

@Composable
fun ButtonFilledWithIcon(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: (() -> Unit)? = {},
    icon: Int = R.drawable.ic_round_double_arrow,
    color: Color = md_theme_light_primary,
) {
    var m = modifier
        .clip(RoundedCornerShape(50))
        .background(color)

    onClick?.let {
        m = m.then(Modifier.clickable { it.invoke() })
    }

    Row(
        modifier = m,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 8.dp,
                start = md_layout_margin + md_text_margin,
            ),
            style = typography.bodySmall,
            text = text.uppercase(Locale.ROOT),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .height(14.dp)
                .padding(
                    start = md_layout_margin,
                    end = md_layout_margin
                ),
            tint = Color.White
        )
    }
}

@Composable
fun ButtonOutlinedWithIcon(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: (() -> Unit)? = null,
    icon: Int = R.drawable.ic_round_double_arrow,
    color: Color = md_theme_light_primary,
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .border(1.dp, color, RoundedCornerShape(50))
            .ifThen(onClick != null) {
                clickable { onClick?.invoke() }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 8.dp,
                start = md_layout_margin + md_text_margin,
            ),
            style = typography.bodySmall,
            text = text.uppercase(Locale.ROOT),
            color = color,
            fontWeight = FontWeight.Bold
        )

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .height(14.dp)
                .padding(
                    start = md_layout_margin,
                    end = md_layout_margin
                ),
            tint = color
        )
    }
}

@Composable
fun ButtonOutlinedSmall(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: androidx.compose.ui.text.TextStyle = typography.bodySmall,
    textColor: Color = md_theme_text_primary,
    backgroundColor: Color = md_theme_light_primaryContainer.copy(alpha = 0.25F),
    strokeColor: Color = Color.Gray,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .border(width = 1.dp, color = strokeColor, shape = RoundedCornerShape(50))
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(
                top = md_drawable_padding,
                bottom = md_drawable_padding,
                start = md_layout_margin,
                end = md_layout_margin
            ),
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPrimaryPreview() {
    AppTheme {
        ButtonPrimary(
            text = "start workout"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonSecondaryPreview() {
    AppTheme {
        ButtonSecondary(
            text = "start workout"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonOutlinedWithIcon() {
    AppTheme {
        ButtonFilledWithIcon(
            text = "test"
        )
    }
}