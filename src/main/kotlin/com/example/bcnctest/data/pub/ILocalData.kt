package com.example.bcnctest.data.pub

import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel

interface ILocalData {

    /**
     * @return List of locally stored albums
     */
    fun getAlbums() : List<AlbumModel>

    /**
     * Save items in @param albums in local storage
     */
    fun saveAlbums(albums: List<AlbumModel>)

    /**
     * @return a list of photos and their details for the given @param albumId album from local storage
     */
    fun getPhotosForAlbum(albumId: String): List<PhotoModel>?

    /**
     * Saves the @param photos details for the @param albumId album in local storage
     */
    fun savePhotosForAlbum(albumId: String, photos: List<PhotoModel>)
}