package com.example.bcnctest.repository

import com.example.bcnctest.BaseTestClass
import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.data.models.PhotoModel
import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.domain.AlbumEntity
import com.example.bcnctest.domain.PhotoEntity
import com.example.bcnctest.exceptions.CustomExceptions
import com.example.bcnctest.repository.imp.Repository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import java.net.URI
import kotlin.test.*

class RepositoryTests : BaseTestClass() {

    @Mock
    private lateinit var localData: ILocalData

    @Mock
    private lateinit var remoteData: IRemoteData

    @InjectMocks
    private lateinit var sut: Repository

    private val mockAlbums = listOf(AlbumModel(id = 0, userId = 0, title = "Mock album title"))

    private val mockPhotos = listOf(
        PhotoModel(
            albumId = 0,
            id = 0,
            title = "Mock photo title",
            url = "http://mockUrl",
            thumbnailUrl = "http://mockThumbnailUrl"
        )
    )

    private val expectedAlbumEntity = AlbumEntity(
        id = "0",
        userId = "0",
        title = "Mock album title",
        photos = listOf(
            PhotoEntity(
                albumId = "0",
                id = "0",
                title = "Mock photo title",
                url = URI("http://mockUrl").toURL(),
                thumbnailUrl = URI("http://mockThumbnailUrl").toURL()
            )
        )
    )

    @BeforeTest
    override fun setUp() {
        super.setUp()

        `when`(localData.getAlbums()).thenReturn(mockAlbums)
        `when`(localData.getPhotosForAlbum(anyString())).thenReturn(mockPhotos)

        `when`(remoteData.getAlbums()).thenReturn(mockAlbums)
        `when`(remoteData.getPhotosForAlbum(anyString())).thenReturn(mockPhotos)
    }

    @Test
    fun `when albums are stored locally then return them`(){
        val albums = sut.getAlbums()


        assertEquals(1, albums.size)
        assertTrue { expectedAlbumEntity == albums[0] }

        verify(remoteData, times(0)).getAlbums()
        verify(remoteData, times(0)).getPhotosForAlbum(anyString())
    }

    @Test
    fun `when album is stored locally but photos are empty then throw PhotosNotAvailable exception`(){
        `when`(localData.getPhotosForAlbum(anyString())).thenReturn(emptyList())

        var exception : Exception? = null
        try {
            sut.getAlbums()
        } catch(e: Exception){
            exception = e
        }

        assertNotNull(exception)
        assertTrue { exception is CustomExceptions.PhotosNotAvailable }

        verify(remoteData, times(0)).getAlbums()
        verify(remoteData, times(0)).getPhotosForAlbum(anyString())
    }

    @Test
    fun `when album is not stored locally then download them from remote`() {
        `when`(localData.getAlbums()).thenReturn(emptyList())
        `when`(localData.getPhotosForAlbum(anyString())).thenReturn(emptyList())

        val albums = sut.getAlbums()

        assertEquals(1, albums.size)
        assertTrue { expectedAlbumEntity == albums[0] }

        verify(remoteData, times(1)).getAlbums()
        verify(remoteData, times(1)).getPhotosForAlbum(anyString())

        verify(localData, times(0)).getPhotosForAlbum(anyString())
        verify(localData, times(1)).saveAlbums(anyList())
        verify(localData, times(1)).savePhotosForAlbum(anyString(), anyList())
    }

    @Test
    fun `when downloading albums but 0 are downloaded then throw AlbumsNotAvailable exception`(){
        `when`(localData.getAlbums()).thenReturn(emptyList())
        `when`(remoteData.getAlbums()).thenReturn(emptyList())

        var exception : Exception? = null
        try {
            sut.getAlbums()
        } catch (e: Exception){
            exception = e
        }

        assertNotNull(exception)
        assertTrue { exception is CustomExceptions.AlbumsNotAvailable }
    }

    @Test
    fun `when downloading photos for an album but 0 are downloaded then throw PhotosNotAvailable exception`(){
        `when`(localData.getAlbums()).thenReturn(emptyList())
        `when`(remoteData.getPhotosForAlbum(anyString())).thenReturn(emptyList())

        var exception : Exception? = null
        try {
            sut.getAlbums()
        } catch (e: Exception){
            exception = e
        }

        assertNotNull(exception)
        assertTrue { exception is CustomExceptions.PhotosNotAvailable }
    }

}