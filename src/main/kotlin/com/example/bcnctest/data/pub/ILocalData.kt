package com.example.bcnctest.data.pub

import com.example.bcnctest.repository.dto.AlbumDto

interface ILocalData {

    /**
     * @return List of locally stored albums
     */
    fun getAlbums() : List<AlbumDto>

    /**
     * Save items in @param albums in local storage
     */
    fun saveAlbums(albums: List<AlbumDto>)
}