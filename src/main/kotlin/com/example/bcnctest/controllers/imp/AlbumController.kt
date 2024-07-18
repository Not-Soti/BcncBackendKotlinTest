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

    @ExceptionHandler(CustomExceptions::class)
    fun handleCustomException(e: CustomExceptions): ResponseEntity<String> =
        ResponseEntity(
            e.toJsonResponse(),
            e.responseCode
        )

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) : ResponseEntity<String> =
        throw CustomExceptions.UnknownException(e.message.toString())
}