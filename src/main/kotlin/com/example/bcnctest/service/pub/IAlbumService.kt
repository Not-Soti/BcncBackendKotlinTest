package com.example.bcnctest.service.pub

import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.model.PhotoEntity

interface IAlbumService {

    fun getAlbums() : List<AlbumEntity>
    fun getPhotosForAlbum(albumId: String): List<PhotoEntity>
}