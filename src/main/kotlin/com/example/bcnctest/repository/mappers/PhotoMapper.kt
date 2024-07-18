package com.example.bcnctest.repository.mappers

import java.net.URI
import com.example.bcnctest.data.models.PhotoModel
import com.example.bcnctest.domain.PhotoEntity

fun PhotoModel.toEntity() =
    PhotoEntity(
        albumId = albumId.toString(),
        id = id.toString(),
        title = title,
        url = URI(url).toURL(),
        thumbnailUrl = URI(thumbnailUrl).toURL()
    )