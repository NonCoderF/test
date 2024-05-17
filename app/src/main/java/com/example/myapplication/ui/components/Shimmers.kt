package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.shimmerBrush
import com.example.myapplication.ui.theme.*

@Composable
fun HorizontalShimmerLoader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val brush = shimmerBrush()

        Box(
            modifier = Modifier
                .padding(
                    start = md_layout_margin,
                    top = md_layout_margin,
                )
                .fillMaxWidth(0.5F)
                .height(md_section_margin)
                .clip(RoundedCornerShape(8.dp))
                .background(brush),
        )

        Box(
            modifier = Modifier
                .padding(
                    start = md_layout_margin,
                    top = md_layout_margin,
                )
                .fillMaxWidth(0.85F)
                .height(md_layout_margin)
                .clip(RoundedCornerShape(8.dp))
                .background(brush),
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            (0..1).forEach {
                Box(
                    modifier = Modifier
                        .padding(
                            start = md_layout_margin,
                            top = md_layout_margin,
                            bottom = md_layout_margin
                        )
                        .weight(1F)
                        .aspectRatio(1.25F)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush),
                )
            }

            Spacer(
                modifier = Modifier.width(md_layout_margin)
            )
        }
    }
}

@Preview
@Composable
fun ShimmerLoaderPreview() {
    HorizontalShimmerLoader()
}