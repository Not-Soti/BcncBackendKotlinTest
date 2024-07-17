package com.example.bcnctest.model

data class AlbumEntity (
    val id: String,
    val userId: String,
    val title: String,
    val photos: List<PhotoEntity>
)