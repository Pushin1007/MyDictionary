package com.pd.mydictionary.model.data

import com.google.gson.annotations.SerializedName

//используем API Skyeng
class DataModel(
    @SerializedName("text") val text: String?, // слово
    @SerializedName("meanings") val meanings: List<Meanings>? //список значений
)