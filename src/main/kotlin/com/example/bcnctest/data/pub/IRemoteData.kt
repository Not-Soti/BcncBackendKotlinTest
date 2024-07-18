package com.example.bcnctest.data.pub

import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel

interface IRemoteData {

    /**
     * @return list of albums from remote storage
     */
    fun getAlbums() : List<AlbumModel>

    /**
     * @return a list of photos and their details for the given @param albumId album from remote storage
     */
    fun getPhotosForAlbum(albumId: String): List<PhotoModel>
}