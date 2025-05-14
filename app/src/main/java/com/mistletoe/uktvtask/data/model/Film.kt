package com.mistletoe.uktvtask.data.model

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("title") var title: String? = null,
    @SerializedName("episode_id") var episodeId: Int? = null,
    @SerializedName("director") var director: String? = null,
    @SerializedName("producer") var producer: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("opening_crawl") var openingCrawl: String? = null
)
