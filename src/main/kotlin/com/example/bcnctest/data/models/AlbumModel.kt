package com.example.bcnctest.data.models

import com.fasterxml.jackson.annotation.JsonProperty

data class AlbumModel(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("userId")
    val userId: Int,

    @JsonProperty("title")
    val title: String
)
