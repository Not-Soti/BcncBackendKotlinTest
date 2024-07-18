package com.example.bcnctest.data

import com.example.bcnctest.BaseTestClass
import com.example.bcnctest.data.imp.LocalData
import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel
import kotlin.test.*

class LocalDataTests : BaseTestClass() {

    private lateinit var sut: LocalData

    private val mockAlbumModel = AlbumModel(
        id = 0,
        userId = 0,
        title = "Mock album title"
    )
    private val mockPhotos = PhotoModel(
        albumId = 0,
        id = 0,
        title = "Mock photo title",
        url = "mock url",
        thumbnailUrl = "mock thumbnail url"
    )

    @BeforeTest
    override fun setUp(){
        sut = LocalData()
    }

    @Test
    fun `When saving an album then it is stored`(){
        sut.saveAlbums(listOf(mockAlbumModel))

        val storedAlbums = sut.getAlbums()

        assertEquals(1, storedAlbums.size)
        assertTrue { mockAlbumModel == storedAlbums[0] }
    }

    @Test
    fun `When saving photos then they are stored`(){
        sut.saveAlbums(listOf(mockAlbumModel))
        sut.savePhotosForAlbum(mockAlbumModel.id.toString(), listOf(mockPhotos))

        val storedPhotos = sut.getPhotosForAlbum(mockAlbumModel.id.toString())

        assertEquals(1, storedPhotos?.size)
        assertTrue { mockPhotos == storedPhotos?.get(0) }
    }

    @Test
    fun `When getting fotos for a non existing album then return null`(){
        val storedPhotos = sut.getPhotosForAlbum(mockAlbumModel.id.toString())
        assertNull(storedPhotos)
    }

}