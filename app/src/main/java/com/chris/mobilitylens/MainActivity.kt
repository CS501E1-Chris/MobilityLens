//starts the app

package com.chris.mobilitylens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

//A class for the six dimensions
data class Dimensions(
    //assigning Int not string, because can't put stringResource() out of composable
    val name: Int,
    val description: Int,
    val implication: Int
)

//then we can create a list of the six dimensions to feed into the objects of the above class
val dim = listOf(
    Dimensions(
        name = R.string.dimension1,
        description = R.string.dimension1_desc,
        implication = R.string.dimension1_imp
    ),
    Dimensions(
        name = R.string.dimension2,
        description = R.string.dimension2_desc,
        implication = R.string.dimension2_imp
    ),
    Dimensions(
        name = R.string.dimension3,
        description = R.string.dimension3_desc,
        implication = R.string.dimension3_imp
    ),
    Dimensions(
        name = R.string.dimension4,
        description = R.string.dimension4_desc,
        implication = R.string.dimension4_imp
    ),
    Dimensions(
        name = R.string.dimension5,
        description = R.string.dimension5_desc,
        implication = R.string.dimension5_imp
    ),
    Dimensions(
        name = R.string.dimension6,
        description = R.string.dimension6_desc,
        implication = R.string.dimension6_imp
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
    //for the displayed text
    var displayText by remember {mutableStateOf("")}
    //for using stringResource() inside events such as onClick

    val context = LocalContext.current
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
        Text(text = "Mobility Lens",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Let's explore the six dimensions that distinguish mobile applications from stationary applications!",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        DisplayDimensions("Dimensions", stringResource(currDimension.name))
        DisplayDimensions("Description", stringResource(currDimension.description))
        DisplayDimensions("Implications", stringResource(currDimension.implication))

        Spacer(modifier = Modifier.height(25.dp))

        //now to include the previous and next buttons
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ){
            //Previous
            Button(
                onClick = {
                    //clearing the text field
                    displayText = ""

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
                    displayText = ""

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

        Spacer(modifier = Modifier.height(50.dp))

        //for the textbox for the user to enter info
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                //intial value
                value = userText,
                //userText gets updated whenever the user types something
                onValueChange = { userText = it },
                //The prompt for the user on what to enter in the text
                label = {
                    Text(
                        text = "Enter your app name",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                modifier = Modifier.weight(1f)
            )

            //for space between the ui elements
            Spacer(modifier = Modifier.width(16.dp))

            val tempDim = stringResource(currDimension.name)

            Button(
                onClick = {
                    if (!userText.isBlank()) {
                        displayText = "$userText : $tempDim"
                    } else {
                        displayText = "Don't leave the field blank!"
                    }
                })
            {
                Text("Submit")
            }
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

@Composable
fun DisplayDimensions(textDim: String, contentDim: String)
{
    Text(
        text = textDim,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, Color.White),
    ) {
        Text(
            text = contentDim,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
}
