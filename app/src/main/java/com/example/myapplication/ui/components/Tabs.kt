package com.example.myapplication.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.md_theme_light_outline
import com.example.myapplication.ui.theme.md_theme_light_primary
import kotlinx.coroutines.launch

data class TabItem(var title: String, var screen: @Composable () -> Unit)

@Composable
fun TabIndicator(tabPosition: List<TabPosition>, index: Int) {
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .tabIndicatorOffset(tabPosition[index])
            .height(4.dp)
            .width(100.dp)
            .background(
                color = md_theme_light_primary,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp
                )
            )
    )
}

@OptIn(ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = md_theme_light_primary,
        indicator = {
            TabIndicator(tabPosition = it, index = pagerState.currentPage)
        }
    ) {

        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(
                    text = tab.title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                ) },
                selectedContentColor = md_theme_light_primary,
                unselectedContentColor = md_theme_light_outline,
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, onClick : (page : Int) -> Unit = {}) {

    var selectedPage by remember{ mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectedPage,
        backgroundColor = Color.White,
        contentColor = md_theme_light_primary,
        indicator = {
            TabIndicator(tabPosition = it, index = selectedPage)
        }
    ) {

        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(
                    text = tab.title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                ) },
                selectedContentColor = md_theme_light_primary,
                unselectedContentColor = md_theme_light_outline,
                selected = selectedPage == index,
                onClick = {
                    selectedPage = index
                    onClick.invoke(index)
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTabs(tabs: List<String>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = md_theme_light_primary,
        indicator = {
            TabIndicator(tabPosition = it, index = pagerState.currentPage)
        },
    ) {

        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(
                    text = tab,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                ) },
                selectedContentColor = md_theme_light_primary,
                unselectedContentColor = md_theme_light_outline,
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTabContent(size : Int, pagerState: PagerState, dragEnabled : Boolean = true, screen: @Composable () -> Unit) {

    HorizontalPager(
        pageCount = size,
        state = pagerState,
        userScrollEnabled = dragEnabled
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            screen.invoke()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, dragEnabled : Boolean = true) {
    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState,
        userScrollEnabled = dragEnabled
    ) {
        tabs[0].screen()
    }
}

@Composable
fun TabsContent(tabs: List<TabItem>, selectedPage : Int) {
    tabs[selectedPage].screen()
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem(
            title = "Ongoing",
            screen = {

            }
        ),
        TabItem(
            title = "Upcoming",
            screen = {

            }
        ),
        TabItem(
            title = "Past",
            screen = {

            }
        )
    )
    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}