//starts the app

package com.chris.mobilitylens

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chris.mobilitylens.ui.theme.MobilityLensTheme

//added
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField


//for formatting
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

//A class for the six dimensions
data class Dimensions(
    val name: String,
    val description: String,
    val implication: String
)

//then we can create a list of the six dimensions to feed into the objects of the above class
val dim = listOf(
    Dimensions(
        name = "Input and Interaction",
        description = "Mobile users interact through touch, gestures, voice, and other input methods.",
        implication = "Design controls that are easy to tap and provide clear feedback."
    ),
    Dimensions(
        name = "Screen Size, Orientation, and Density",
        description = "Mobile devices have different screen sizes, orientations, and pixel densities.",
        implication = "Use responsive layouts and scalable dimensions so content remains usable."
    ),
    Dimensions(
        name = "Lifecycle and Resource Constraints",
        description = "",
        implication = ""
    ),
    Dimensions(
        name = "Security and Privacy Expectations",
        description = "",
        implication = ""
    ),
    Dimensions(
        name = "Context Awareness",
        description = "",
        implication = ""
    ),
    Dimensions(
        name = "Usage Patterns",
        description = "",
        implication = ""
    ),
)


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

//composable is like a package that allows the function certain functionalities like Text, Button, etc
//for the UI
@Composable
fun DisplayMobilityLens(innerPadding: PaddingValues) {

    //asked AI for help with the below concept
    //mutable state is a value that is changeable so that
    //compose watches this and knows when the UI should change accordingly
    //in this case, we have the "currIndex" val that changes to switch to the next dimension
    var currIndex by remember {mutableStateOf(0)}
    var currDimension = dim[currIndex]
    //for the text field
    var userText by remember {mutableStateOf("")}
    //for the disppayed text
    var displayText by remember {mutableStateOf("")}
    //Column arranges the UI elements vertically
    Column(
        modifier = Modifier
            //fill the maxiumum of the screen
            .fillMaxSize()
            //padding included so it does not get placed near the edges of the screen
            .padding(innerPadding)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ){
        Text("Mobility Lens")
        Text(
            text = "Let's explore the six dimensions that distinguish mobile applications from stationary applications!",
            //bodyLarge is a style for title headings
            style = MaterialTheme.typography.bodyLarge
        )

        Text("Dimension: ${currDimension.name}")
        Text("Description: ${currDimension.description}")
        Text("Description: ${currDimension.implication}")

        //now to include the previous and next buttons

        Row {
            //Previous
            Button(
                onClick = {
                    if (currIndex == 0) {
                        currIndex = dim.size - 1
                    } else {
                        currIndex--
                    }
                }
            )
            {
                Text("Previous")
            }

            //Next
            Button(
                onClick = {
                    if (currIndex == dim.size - 1) {
                        currIndex = 0
                    } else {
                        currIndex++
                    }
                }
            )
            {
                Text("Next")
            }
        }

        //for the textbox for the user to enter info
        OutlinedTextField(
            //intial value
            value = userText,
            //userText gets updated whenever the user types something
            onValueChange = {userText = it},
            //The prompt for the user on what to enter in the text
            label = {Text("Enter your app name")},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

        //for space between the ui elements
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(!userText.isBlank())
                {
                    displayText = userText + " : " + currDimension.name
                }
                else{
                    displayText = "Don't leave the field blank!"
                }
            })
            {
                Text("Submit")
            }

        //To display the user text with the current dimension info
        OutlinedTextField(
            value = displayText,
            //we want it to be read only
            onValueChange = {},
            label = {},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}

//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobilityLensTheme {
//        Greeting("Android")
//    }
//}