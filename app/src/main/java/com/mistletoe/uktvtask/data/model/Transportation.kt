package com.mistletoe.uktvtask.data.model

import com.google.gson.annotations.SerializedName

data class Transportation(
    @SerializedName("name") var name: String? = null,
    @SerializedName("model") var model: String? = null,
    @SerializedName("manufacturer") var manufacturer: String? = null,
    @SerializedName("cost_in_credits") var costInCredits: String? = null,
    @SerializedName("length") var length: String? = null,
    @SerializedName("crew") var crew: String? = null,
    @SerializedName("passengers") var passengers: String? = null,
    @SerializedName("cargo_capacity") var cargoCapacity: String? = null
)
