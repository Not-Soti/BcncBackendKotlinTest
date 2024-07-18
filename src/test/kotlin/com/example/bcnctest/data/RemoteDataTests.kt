package com.example.bcnctest.data

import com.example.bcnctest.BaseTestClass
import com.example.bcnctest.data.imp.RemoteData
import com.example.bcnctest.data.models.AlbumModel
import com.example.bcnctest.exceptions.CustomExceptions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RemoteDataTests : BaseTestClass() {

    @Mock
    private lateinit var restTemplate: RestTemplate

    @InjectMocks
    private lateinit var sut: RemoteData

    private val mockAlbumModel = AlbumModel(
        id = 0,
        userId = 0,
        title = "Mock album title"
    )

    @BeforeTest
    override fun setUp(){
        super.setUp()

        //Set up default mocks
        Mockito.`when`(
            restTemplate.exchange(
                anyString(),
                any(),
                isNull(),
                any<ParameterizedTypeReference<List<AlbumModel>>>()
            )
        ).thenReturn(ResponseEntity.ok(listOf(mockAlbumModel)))
    }

    @Test
    fun `When getting albums and they are correctly fetched then return them`() {
        val result = sut.getAlbums()

        assertEquals(1, result.size)
        assertEquals(mockAlbumModel, result[0])
    }

    @Test
    fun `When getting albums and the rest client fails then throw RestClientException`() {
        Mockito.`when`(
            restTemplate.exchange(
                anyString(),
                any(),
                isNull(),
                any<ParameterizedTypeReference<List<AlbumModel>>>()
            )
        ).thenThrow(RestClientException("RestClientException"))

        var exception: Exception? = null
        try{
            sut.getAlbums()
        } catch (e: CustomExceptions.RestClientException){
            exception = e
        }

        assertNotNull(exception)
        assertTrue { exception is CustomExceptions.RestClientException }
        assertEquals("RestClientException", exception.message )
    }

    @Test
    fun `When getting albums and albums are empty then throw AlbumsNotAvailable exception`() {
        Mockito.`when`(
            restTemplate.exchange(
                anyString(),
                any(),
                isNull(),
                any<ParameterizedTypeReference<List<AlbumModel>>>()
            )
        ).thenReturn(ResponseEntity.ok(emptyList()))

        var exception: Exception? = null
        try{
            sut.getAlbums()
        } catch (e: CustomExceptions.AlbumsNotAvailable){
            exception = e
        }

        assertNotNull(exception)
        assertTrue { exception is CustomExceptions.AlbumsNotAvailable }
    }

    /*
     * TODO: Do similar tests for getPhotosForAlbum() method
     */
}