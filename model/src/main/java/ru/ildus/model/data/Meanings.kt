package ru.ildus.model.data

import com.google.gson.annotations.SerializedName

 data class Meanings(
    @field:SerializedName("translation")
    val translation: Translation = Translation(),
    @field:SerializedName("imageUrl")
    val imageUrl: String = ""
)
