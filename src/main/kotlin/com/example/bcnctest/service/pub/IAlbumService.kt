package com.example.bcnctest.service.pub

import com.example.bcnctest.domain.AlbumEntity

interface IAlbumService {

    /**
     * @return the album list and their images from the system.
     */
    fun getAlbums() : List<AlbumEntity>
}