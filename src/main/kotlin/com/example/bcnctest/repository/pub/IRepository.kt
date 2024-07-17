package com.example.bcnctest.repository.pub

import com.example.bcnctest.model.AlbumEntity

interface IRepository {

    /**
     * @return list of available albums
     */
    fun getAlbums() : List<AlbumEntity>
}