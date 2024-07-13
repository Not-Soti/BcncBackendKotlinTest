package com.example.bcnctest.data.pub

import com.example.bcnctest.data.dto.AlbumDto
import com.example.bcnctest.data.dto.PhotoDto

interface ILocalData {

    /**
     * @return List of locally stored albums
     */
    fun getAlbums() : List<AlbumDto>

    /**
     * Save items in @param albums in local storage
     */
    fun saveAlbums(albums: List<AlbumDto>)
    fun getPhotosForAlbum(albumId: String): List<PhotoDto>?
    fun savePhotosForAlbum(albumId: String, photos: List<PhotoDto>)
}