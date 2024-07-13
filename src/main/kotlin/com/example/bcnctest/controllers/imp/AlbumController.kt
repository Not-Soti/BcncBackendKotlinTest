package com.example.bcnctest.controllers.imp

import com.example.bcnctest.controllers.pub.IAlbumController
import com.example.bcnctest.exceptions.CustomExceptions
import com.example.bcnctest.service.pub.IAlbumService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/albums")
class AlbumController(
    private val service: IAlbumService
) : IAlbumController {

    @GetMapping
    override fun getAlbums() = service.getAlbums()

    @GetMapping("{albumId}/photos")
    override fun getPhotosForAlbum(@PathVariable albumId: String) = service.getPhotosForAlbum(albumId)


    @ExceptionHandler(CustomExceptions.AlbumsNotAvailable::class)
    fun handleDuplicatedId(e: CustomExceptions.AlbumsNotAvailable): ResponseEntity<String> =
        ResponseEntity(
            e.toJsonResponse(),
            e.responseCode
        )
}