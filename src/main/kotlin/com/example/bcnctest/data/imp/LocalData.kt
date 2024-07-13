package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.dto.AlbumDto
import com.example.bcnctest.data.dto.PhotoDto

class LocalData : ILocalData {

    private val albums = mutableListOf<AlbumDto>()

    private val photosInAlbum = mutableMapOf<String, List<PhotoDto>>()

    override fun getAlbums(): List<AlbumDto> = albums

    override fun saveAlbums(albums: List<AlbumDto>) {
        this.albums.addAll(albums)
    }

    override fun getPhotosForAlbum(albumId: String) = photosInAlbum[albumId]

    override fun savePhotosForAlbum(albumId: String, photos: List<PhotoDto>) {
        photosInAlbum[albumId] = photos
    }
}