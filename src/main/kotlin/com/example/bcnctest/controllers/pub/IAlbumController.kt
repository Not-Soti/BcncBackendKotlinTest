package com.example.bcnctest.controllers.pub

import com.example.bcnctest.domain.AlbumEntity

interface IAlbumController {

    /**
     * Get all albums and its photos from the system
     * On success: @return a list of albums with their details, including a list of their photos
     * On failure: @return information about the exception handled
     */
    fun getAlbums() : List<AlbumEntity>
}