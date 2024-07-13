package com.example.bcnctest.controllers.imp

import com.example.bcnctest.controllers.pub.IAlbumController
import com.example.bcnctest.service.pub.IAlbumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/albums")
class AlbumController(
    private val service: IAlbumService
) : IAlbumController {

    @GetMapping
    override fun getAlbums() = service.getAlbums()

    /*
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleDuplicatedId(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(
            "{" +
                    "\"Exception\":\"IllegalArgumentException\"," +
                    "\"cause\": \"Duplicated id\"" +
                    "}",
            HttpStatus.BAD_REQUEST
        )

    @GetMapping("greeting")
    fun helloWorld() = "Hello from the HelloWorldController"

    @GetMapping("entities")
    fun getEntities(): List<ExampleEntity> {
        val entities = service.getExampleEntities()

        return entities
    }

    @GetMapping("entity/{id}")
    fun getEntityById(@PathVariable id: String): ResponseEntity<Any> {
        val entity = service.getExampleEntities().find { it.id == id }

        return if (entity != null) {
            ResponseEntity.ok(entity)
        } else {
            ResponseEntity(
                "{" +
                        "\"Exception\":\"IllegalArgumentException\"," +
                        "\"cause\": \"Duplicated id\"" +
                        "}",
                HttpStatus.NOT_FOUND
            )
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun addEntity(@RequestBody entity: ExampleEntity) = service.addEntity(entity)

    @GetMapping("pokemon")
    fun getPokemon() = service.getPokemon()
    */

}