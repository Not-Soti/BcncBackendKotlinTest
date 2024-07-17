package com.example.bcnctest.controllers.pub

import com.example.bcnctest.model.AlbumEntity

interface IAlbumController {

    fun getAlbums() : List<AlbumEntity>
}