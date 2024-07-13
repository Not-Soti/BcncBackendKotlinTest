package com.example.bcnctest.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class CustomExceptions(
    val responseCode: HttpStatusCode,
    val name: String,
    override val message: String
) : Exception() {

    class AlbumsNotAvailable : CustomExceptions(
        HttpStatus.NOT_FOUND,
        "NotAlbumsAvailable",
        "There are no albums available in the system"
    )

    class PhotosNotAvailable(albumId: String) : CustomExceptions(
        HttpStatus.NOT_FOUND,
        "PhotosNotAvailable",
        "There are no photos available for album with id $albumId"
    )

    class RestClientException(message: String) : CustomExceptions(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "RestClientException",
        message
    )

    fun toJsonResponse(): String =
        "{" +
        "\"exception\":\" ${this.name}\"," +
        "\"message\": \" ${this.message}\"" +
        "}"

}
