Este proyecto es el resultado de la implementación de una API en Kotlin y Springboot, la cual ofrece a través de un único endpoint información sobre álbumes de fotos y sus propias fotos.

El proyectose ha construido siguiendo una arquitectura Clean Architecture, dividido en varias capas:
- La capa de Controllers expone los endpoints de acceso a la API, y obtiene información de la capa Service.
- La capa service obtiene información de la capa Repository, y la transforma si es necesario para entregar una respuesta correcta.
- La capa repository se encarga de hacer transparente a los servicios el origen de la información, obteniendola de la capa Data a través de un origen remoto y un origen local.
  Al hacer la petición ded obtener las imagenes por primera vez, se obtienen de la API remota necesaria mediante la clase RemoteData, y se cachean localmente mediante LocalData.
  Para las posteriores peticiones de los álbumes ded fotos, la información se devuelve consultando solo a la capa de almacenamiento local, agilizando el proceso y reduciendo los recursos utilizados al reducir el número de llamadas necesarias a APIs externas.

El lanzamiento de la app se hace a través del método main() encontado en el archivo BcncTestApplication.kt.
Los test se han desarrollado bajo una estructura de carpetas similar. En este caso, no se han realizado tests que no aporten valor para el desarrollo actual como el de la clase AlbumService, ya que solo se encarga de devolver la información procedente del repositorio sin hacer ningún tipo de transformación.

Al hacer una llamada al endpoint http://localhost:8080/api/albums se pueden obtener 2 respuestas:
- 200 - OK:
  Se han recibido correctamente la información de los álbumes y las imágenes.
- 4XX - 5XX:
  Ha ocurrido un error. En la respuesta se envía el tipo de excepción interna que ha habido en el servidor junto con un mensaje explicativo.

La documentación se puede ver en el siguiente enlace: https://app.swaggerhub.com/apis/SERGITM/BcncTest/1.0.0

Algunos aspectos de mejora son los siguientes:
- Utilizar la capa de Service para pulir la respuesta que se va a enviar al cliente en vez de enviar toda la información de AlbumEntity y PhotoEntity, en caso de que no necesite algún dato como el id del album que se envía con cada foto.
- Almacenar la información en una base de datos en lugar de en memoria principal, para que sobrevida a rearranques del servicio.
- Modularizar la aplicación de forma que cada capa sea un submódulo de Gradle propio en caso de que la aplicación crezca, evitando compilar todo el código cada vez que se hace un cambio.
