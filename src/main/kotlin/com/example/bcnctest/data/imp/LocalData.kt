package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel

class LocalData : ILocalData {

    private val albums = mutableListOf<AlbumModel>()

    private val photosInAlbum = mutableMapOf<String, List<PhotoModel>>()

    override fun getAlbums(): List<AlbumModel> = albums

    override fun saveAlbums(albums: List<AlbumModel>) {
        this.albums.addAll(albums)
    }

    override fun getPhotosForAlbum(albumId: String) = photosInAlbum[albumId]

    override fun savePhotosForAlbum(albumId: String, photos: List<PhotoModel>) {
        photosInAlbum[albumId] = photos
    }
}