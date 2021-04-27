package com.example.pfinalsw.domain.model

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("results")
    var results : ArrayList<People>,
    @SerializedName("next")
    var next : String,
    @SerializedName("previous")
    var previous : String)
