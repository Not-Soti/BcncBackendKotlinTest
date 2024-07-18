package com.example.bcnctest.controllers

import com.example.bcnctest.BaseTestClass
import com.example.bcnctest.domain.AlbumEntity
import com.example.bcnctest.domain.PhotoEntity
import com.example.bcnctest.exceptions.CustomExceptions
import com.example.bcnctest.service.imp.AlbumService
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.`when`
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.net.URI
import java.util.stream.Stream
import kotlin.test.BeforeTest
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTests : BaseTestClass() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var albumService: AlbumService

    private val mockAlbumList = listOf(
        AlbumEntity(
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
    )

    @BeforeTest
    override fun setUp() {
        super.setUp()
        `when`(albumService.getAlbums()).thenReturn(mockAlbumList)
    }

    companion object {
        @JvmStatic
        fun mockExceptionNames(): Stream<Pair<CustomExceptions, String>> {
            return Stream.of(
                CustomExceptions.AlbumsNotAvailable() to "AlbumsNotAvailableException",
                CustomExceptions.PhotosNotAvailable("0") to "PhotosNotAvailableException",
                CustomExceptions.RestClientException("mock message") to "RestClientException",
                CustomExceptions.UnknownException("mock message") to "UnknownException"
            )
        }
    }

    @Test
    fun `when calling getAlbums then return albums successfully`() {
        mockMvc.get("/api/albums")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(0) }
                jsonPath("$[0].userId") { value(0) }
                jsonPath("$[0].title") { value("Mock album title") }
                jsonPath("$[0].photos[0].albumId") { value(0) }
                jsonPath("$[0].photos[0].id") { value(0) }
                jsonPath("$[0].photos[0].title") { value("Mock photo title") }
                jsonPath("$[0].photos[0].url") { value("http://mockUrl") }
                jsonPath("$[0].photos[0].thumbnailUrl") { value("http://mockThumbnailUrl") }
            }
    }

    @ParameterizedTest
    @MethodSource("mockExceptionNames")
    fun `when service throws custom exception then response handles it`(exception: Pair<CustomExceptions, String>){
        val exceptionObject = exception.first
        val exceptionName = exception.second

        given(albumService.getAlbums()).willAnswer{ throw exceptionObject }

        mockMvc.get("/api/albums")
            .andExpect {
                jsonPath("exception") { value(exceptionName) }
            }
    }
}