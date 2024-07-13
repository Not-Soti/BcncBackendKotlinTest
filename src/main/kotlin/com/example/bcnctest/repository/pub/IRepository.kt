package com.example.bcnctest.repository.pub

import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.model.PhotoEntity

interface IRepository {

    /**
     * @return list of available albums
     */
    fun getAlbums() : List<AlbumEntity>
    fun getPhotosForAlbum(albumId: String): List<PhotoEntity>
}