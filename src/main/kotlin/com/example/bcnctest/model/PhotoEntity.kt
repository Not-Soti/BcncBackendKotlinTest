package com.example.bcnctest.model

import java.net.URL

data class PhotoEntity (
    val albumId : String,
    val id : String,
    val title: String,
    val url: URL,
    val thumbnailUrl: URL
)