package com.example.bcnctest.data.pub

import com.example.bcnctest.data.dto.AlbumDto
import com.example.bcnctest.data.dto.PhotoDto

interface IRemoteData {

    /**
     * @return list of albums from remote storage
     */
    fun getAlbums() : List<AlbumDto>
    fun getPhotosForAlbum(albumId: String): List<PhotoDto>
}