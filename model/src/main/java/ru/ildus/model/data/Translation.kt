package ru.ildus.model.data

import com.google.gson.annotations.SerializedName

 data class Translation(
    @field:SerializedName("text")
    val translation: String = ""
)
