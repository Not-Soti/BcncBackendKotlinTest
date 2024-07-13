package com.example.bcnctest.repository.imp

import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.exceptions.CustomExceptions
import com.example.bcnctest.model.AlbumEntity
import com.example.bcnctest.model.PhotoEntity
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
        var albums = localData.getAlbums()

        //If albums are not stored locally then get from remote and cache them
        if(albums.isEmpty()){
            albums = remoteData.getAlbums()

            if(albums.isEmpty()){
                throw CustomExceptions.AlbumsNotAvailable()
            }

            localData.saveAlbums(albums)
        }

        return albums.map { it.toEntity() }
    }

    override fun getPhotosForAlbum(albumId: String) : List<PhotoEntity>{
        var photos = localData.getPhotosForAlbum(albumId)

        if(photos == null){
            photos = remoteData.getPhotosForAlbum(albumId)

            if(photos.isEmpty()){
                throw CustomExceptions.PhotosNotAvailable(albumId)
            }

            localData.savePhotosForAlbum(albumId, photos)
        }

        return photos.map { it.toEntity() }
    }

}