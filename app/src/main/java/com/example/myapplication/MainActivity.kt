package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.md_layout_margin
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.7F)
                                .height(56.dp),
                            onClick = {
                                throw Exception("Something went wrong")
                            }) {
                            Text(text = "I am button to crash")
                        }

                        Button(
                            modifier = Modifier
                                .padding(top = md_layout_margin)
                                .fillMaxWidth(0.7F)
                                .height(56.dp),
                            onClick = {
                                Crashes.generateTestCrash()
                            }) {
                            Text(text = "I am button to generate crash")
                        }

                        Button(
                            modifier = Modifier
                                .padding(top = md_layout_margin)
                                .fillMaxWidth(0.7F)

                                .height(56.dp),
                            onClick = {
                                Analytics.trackEvent("track evnt")
                            }) {
                            Text(text = "I am button to track event")
                        }
                    }
                }
            }
        }



        AppCenter.start(application,
            "0dd8a356-8187-4fbc-a8c6-2841bdee8300",
            Analytics::class.java,
            Crashes::class.java
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}