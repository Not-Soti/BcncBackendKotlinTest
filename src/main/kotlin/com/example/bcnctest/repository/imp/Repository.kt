package com.example.bcnctest.repository.imp

import com.example.bcnctest.data.dto.AlbumDto
import com.example.bcnctest.data.dto.PhotoDto
import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.exceptions.CustomExceptions
import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.repository.mappers.toEntity
import com.example.bcnctest.repository.pub.IRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class Repository(
    @Autowired private val localData: ILocalData,
    @Autowired private val remoteData: IRemoteData
) : IRepository {

    override fun getAlbums(): List<AlbumEntity> {
        var fetchedAlbums = localData.getAlbums()
        val albums = mutableListOf<AlbumEntity>()

        //If albums are not stored locally then get from remote and cache them
        if (fetchedAlbums.isEmpty()) {
            fetchedAlbums = downloadAlbums()
            for (album in fetchedAlbums) {
                val fetchedPhotos = downloadPhotosForAlbum(album.id.toString())
                albums.add(
                    AlbumEntity(
                        id = album.id.toString(),
                        userId = album.userId.toString(),
                        title = album.title,
                        photos = fetchedPhotos.map { it.toEntity() }
                    )
                )
            }
            return albums
        } else {
            for (album in fetchedAlbums) {
                val fetchedPhotos = localData.getPhotosForAlbum(album.id.toString())
                if (fetchedPhotos.isNullOrEmpty()) {
                    throw CustomExceptions.PhotosNotAvailable(album.id.toString())
                }
                albums.add(
                    AlbumEntity(
                        id = album.id.toString(),
                        userId = album.userId.toString(),
                        title = album.title,
                        photos = fetchedPhotos.map { it.toEntity() }
                    )
                )
            }
            return albums
        }
    }

    private fun downloadAlbums(): List<AlbumDto> {
        val albums = remoteData.getAlbums()
        if (albums.isEmpty()) {
            throw CustomExceptions.AlbumsNotAvailable()
        }
        localData.saveAlbums(albums)

        return albums
    }

    private fun downloadPhotosForAlbum(albumId: String): List<PhotoDto> {
        var photos = localData.getPhotosForAlbum(albumId)

        if (photos == null) {
            photos = remoteData.getPhotosForAlbum(albumId)
            if (photos.isEmpty()) {
                throw CustomExceptions.PhotosNotAvailable(albumId)
            }

            localData.savePhotosForAlbum(albumId, photos)
        }

        return photos
    }

}