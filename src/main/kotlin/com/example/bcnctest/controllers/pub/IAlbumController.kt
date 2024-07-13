package com.example.bcnctest.controllers.pub

import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.model.PhotoEntity

interface IAlbumController {

    fun getAlbums() : List<AlbumEntity>
    fun getPhotosForAlbum(albumId: String): List<PhotoEntity>
}