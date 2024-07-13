package com.example.bcnctest.data.pub

import com.example.bcnctest.repository.dto.AlbumDto

interface IRemoteData {

    /**
     * @return list of albums from remote storage
     */
    fun getAlbums() : List<AlbumDto>
}