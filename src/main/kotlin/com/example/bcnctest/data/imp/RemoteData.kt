package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.repository.dto.AlbumDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class RemoteData : IRemoteData {
    private lateinit var restTemplate: RestTemplate

    fun initialize(restTemplate: RestTemplate){
        this.restTemplate = restTemplate
    }

    override fun getAlbums(): List<AlbumDto> {
        val albums : ResponseEntity<List<AlbumDto>> =
            restTemplate.exchange(
                Constants.JsonPlaceholderApi.albums,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<AlbumDto>>(){}
            )

        return albums.body ?: emptyList() //TODO - handle errors
    }

}