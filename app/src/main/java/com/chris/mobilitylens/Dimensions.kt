package com.chris.mobilitylens

//A class for the six dimensions
data class Dimensions(
    //assigning Int not string, because can't put stringResource() out of composable
    val name: Int,
    val description: Int,
    val implication: Int
)

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
