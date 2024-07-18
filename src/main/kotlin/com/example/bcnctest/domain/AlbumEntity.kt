package com.example.bcnctest.domain

data class AlbumEntity (
    val id: String,
    val userId: String,
    val title: String,
    val photos: List<PhotoEntity>
)