package com.example.bcnctest.repository.mappers

import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.data.dto.AlbumDto

fun AlbumDto.toEntity() =
    AlbumEntity(
        id = id.toString(),
        userId = userId.toString(),
        title = title
    )