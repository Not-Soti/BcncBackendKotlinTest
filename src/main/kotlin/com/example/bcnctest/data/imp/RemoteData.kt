package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel
import com.example.bcnctest.exceptions.CustomExceptions
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

class RemoteData : IRemoteData {
    private lateinit var restTemplate: RestTemplate

    fun initialize(restTemplate: RestTemplate) {
        this.restTemplate = restTemplate
    }

    override fun getAlbums(): List<AlbumModel> {
        val albums: ResponseEntity<List<AlbumModel>>

        try {
            albums =
                restTemplate.exchange(
                    Constants.JsonPlaceholderApi.albums,
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<AlbumModel>>() {}
                )
        } catch (e: RestClientException) {
            throw CustomExceptions.RestClientException(e.message ?: "Unknown root cause getting albums")
        }

        if (albums.body.isNullOrEmpty()) {
            throw CustomExceptions.AlbumsNotAvailable()
        }

        return albums.body!!
    }


    override fun getPhotosForAlbum(albumId: String): List<PhotoModel> {
        val photos: ResponseEntity<List<PhotoModel>>
        try{
            photos =
                restTemplate.exchange(
                    Constants.JsonPlaceholderApi.photos,
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<PhotoModel>>() {},
                    albumId
                )
        } catch (e: RestClientException) {
            throw CustomExceptions.RestClientException(e.message ?: "Unknown root cause getting photos for album")
        }

        if(photos.body == null){
            throw CustomExceptions.PhotosNotAvailable(albumId)
        }

        return photos.body!!
    }
}