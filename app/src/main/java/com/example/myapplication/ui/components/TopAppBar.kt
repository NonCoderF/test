package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.myapplication.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title : String,
    onBackPressed : () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        modifier = Modifier
            .zIndex(1f)
            .shadow(AppBarDefaults.TopAppBarElevation),
        navigationIcon = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ){
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = actions
    )
}

@Composable
fun OverflowMenu(
    content : @Composable () -> Unit = {},
    onDismissRequest : () -> Unit = {},
    expanded : Boolean = false,
    expandedCallback : (Boolean) -> Unit = {}
) {

    var showMenu by remember { mutableStateOf(false) }

    LaunchedEffect(expanded){
        showMenu = expanded
    }

    IconButton(onClick = {
        showMenu = !showMenu
        expandedCallback.invoke(showMenu)
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "",
            tint = Color.Black
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = {
            showMenu = false
            onDismissRequest.invoke()
            expandedCallback.invoke(showMenu)
        }
    ) {
        content()
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    AppTheme {
        Toolbar(
            title = "start workout"
        )
    }
}