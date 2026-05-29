package com.example.news.presentation.components

import androidx.annotation.DrawableRes
import com.example.news.R

data class Page(
    val title : String,
    val discription : String,
    @DrawableRes val image : Int
)


    val pages= listOf(
        Page(
            title = "Architecture News is simple dummy",
            discription = "Architecture is the art and science of designing buildings.\n" +
                    "It reflects human creativity and history.",
            image =R.drawable.archi
        ),
        Page(
            title = "Nature News is simple dummy",
            discription = "Nature provides us with fresh air, peace, and life.\n" +
                    "Its beauty and balance help sustain the Earth.",
            image =R.drawable.nature
        )
    )

