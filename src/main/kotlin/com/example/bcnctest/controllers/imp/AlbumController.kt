package com.example.bcnctest.controllers.imp

import com.example.bcnctest.controllers.pub.IAlbumController
import com.example.bcnctest.service.pub.IAlbumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/albums")
class AlbumController(
    private val service: IAlbumService
) : IAlbumController {

    @GetMapping
    override fun getAlbums() = service.getAlbums()

    @GetMapping("{albumId}/photos")
    override fun getPhotosForAlbum(@PathVariable albumId: String) = service.getPhotosForAlbum(albumId)
}