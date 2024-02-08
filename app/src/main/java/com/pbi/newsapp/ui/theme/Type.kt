package com.pbi.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pbi.newsapp.R

val Quicksand = FontFamily(
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semi_bold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle( // title
        fontFamily = Quicksand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    bodyMedium = TextStyle( // description
        fontFamily = Quicksand,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    displayMedium = TextStyle( // author
        fontFamily = Quicksand,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    displaySmall = TextStyle( // source
        fontFamily = Quicksand,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle( // published at
        fontFamily = Quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

)