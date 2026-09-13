//file containing code for the screen that displays different dimensions
//references Dimensions.kt which contains the class and list for dimension content

package com.chris.mobilitylens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll



    //composable is like a package that allows the function certain functionalities like Text, Button, etc.
    //for the UI
    @Composable
    fun DisplayMobilityLens(innerPadding: PaddingValues) {

        //asked AI for help with the below concept
        //mutable state is a value that is changeable so that
        //compose watches this and knows when the UI should change accordingly
        //in this case, we have the "currIndex" val that changes to switch to the next dimension
        var currIndex by remember { mutableIntStateOf(0) }
        val currDimension = dim[currIndex]
        //for the text field
        var userText by remember {mutableStateOf("")}
        //for the displayed text
        var displayText by remember {mutableStateOf("")}

        //Column arranges the UI elements vertically
        Column(
            modifier = Modifier
                //fill the maximum with the screen
                .fillMaxSize()
                //padding included so it does not get placed near the edges of the screen
                .padding(innerPadding)
                .padding(24.dp)
                //since the user and display text field was getting cut off because of the dimensions
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ){
            Text(text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = stringResource(R.string.intro),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            DisplayDimensions(stringResource(R.string.dimension), stringResource(currDimension.name))
            DisplayDimensions(stringResource(R.string.description), stringResource(currDimension.description))
            DisplayDimensions(stringResource(R.string.implication), stringResource(currDimension.implication))

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
                    //initial value
                    value = userText,
                    //userText gets updated whenever the user types something
                    onValueChange = { userText = it },
                    //The prompt for the user on what to enter in the text
                    label = {
                        Text(
                            text = stringResource(R.string.user_field),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier.weight(1f)
                )

                //for space between the ui elements
                Spacer(modifier = Modifier.width(16.dp))

                val tempDim = stringResource(currDimension.name)
                val tempFeedback = stringResource(R.string.feedback)

                Button(
                    onClick = {
                        displayText = if (!userText.isBlank()) {
                            "$userText : $tempDim"
                        } else {
                            tempFeedback
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

    //for displaying the dimensions
    @Composable
    fun DisplayDimensions(textDim: String, contentDim: String)
    {
        Text(
            text = textDim,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
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