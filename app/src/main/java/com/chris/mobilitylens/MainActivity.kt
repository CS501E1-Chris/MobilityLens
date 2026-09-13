//starts the app

package com.chris.mobilitylens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chris.mobilitylens.ui.theme.MobilityLensTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setContent{} tells Android what UI to display
        setContent {
            MobilityLensTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayMobilityLens(innerPadding)
                }
            }
        }
    }
}


//for viewing a preview of the screen
@Preview(showBackground = true)
@Composable
fun DisplayMobilityLensPreview()
{
    MobilityLensTheme{
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> DisplayMobilityLens(innerPadding) }
        DisplayMobilityLens(innerPadding = PaddingValues(0.dp))
    }
}

