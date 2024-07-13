package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.repository.dto.AlbumDto

class LocalData : ILocalData {

    private val storedAlbums = mutableListOf<AlbumDto>()

    override fun getAlbums(): List<AlbumDto> = storedAlbums

    override fun saveAlbums(albums: List<AlbumDto>) {
        storedAlbums.addAll(albums)
    }

}