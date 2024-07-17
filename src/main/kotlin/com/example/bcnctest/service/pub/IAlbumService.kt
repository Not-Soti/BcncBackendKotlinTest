package com.example.bcnctest.service.pub

import com.example.bcnctest.model.AlbumEntity

interface IAlbumService {

    fun getAlbums() : List<AlbumEntity>
}