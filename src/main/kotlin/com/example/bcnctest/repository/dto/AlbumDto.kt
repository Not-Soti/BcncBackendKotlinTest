package com.example.bcnctest.repository.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AlbumDto(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("userId")
    val userId: Int,

    @JsonProperty("title")
    val title: String
)
