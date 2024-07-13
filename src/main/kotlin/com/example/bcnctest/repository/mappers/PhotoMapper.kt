package com.example.bcnctest.repository.mappers

import java.net.URI
import com.example.bcnctest.data.dto.PhotoDto
import com.example.bcnctest.model.PhotoEntity

fun PhotoDto.toEntity() =
    PhotoEntity(
        albumId = albumId.toString(),
        id = id.toString(),
        title = title,
        url = URI(url).toURL(),
        thumbnailUrl = URI(thumbnailUrl).toURL()
    )