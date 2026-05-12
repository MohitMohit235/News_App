package com.example.news.presentation.onbording

import androidx.annotation.DrawableRes
import com.example.news.R

data class Page(
    val title : String,
    val discription : String,
    @DrawableRes val image : Int
)


    val pages= listOf(
        Page(
            title = "Food News is simple dummy",
            discription = "Food is not just for satisfying hunger, but also a part of culture and emotions.\n" +
                             "Every dish represents a unique story and tradition.",
            image =R.drawable.food
        ),
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

