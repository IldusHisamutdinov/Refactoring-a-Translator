package ru.ildus.model.data

import com.google.gson.annotations.SerializedName

class Translation(
    @field:SerializedName("text")
    val translation: String?
)
