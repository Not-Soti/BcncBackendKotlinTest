package com.example.bcnctest.service.imp

import com.example.bcnctest.repository.pub.IRepository
import com.example.bcnctest.service.pub.IAlbumService
import org.springframework.stereotype.Service

@Service
class AlbumService(
    private val repository: IRepository
) : IAlbumService {

    override fun getAlbums() = repository.getAlbums()
}