
# API-REST Java

¡Hola! La API fue hecha usando Java con su framework 'Spring Boot' usando como patrón de arquitectura MVC. Utiliza: 
* Junit & Mockito para testing.
* Hibernate como ORM.
* Postgresql como base de datos.
* Docker para levantar la base de datos.
* Swagger para documentar la API. (próximamente será reemplazado por 'Bruno')



## Como levantar el proyecto
Una vez clones el repositorio en tu computadora, tendrás que crear un archivo ".env" y agregar tus credenciales, en el archivo:
".env-template" podrás ver el formato a seguir y cambiar el nombre de la base de datos y/o puerto si es necesario.

Una vez abras el proyecto con tu IDE de preferencia, tendrás que ejecutar en la terminal el comando:
* ```docker-compose up -d``` para levantar la base de datos Postgres mediante Docker.
¡Luego simplemente corremos el programa y comenzamos a hacer las pruebas!

Ingresando en "http://localhost:8181/swagger-ui/index.html/" vas a poder ver los métodos disponibles e información sobre su uso. Recorda que si cambiaste el puerto a utilizar tendrás que cambiarlo también en la URL.
## Autor

- [@JustAKing12](https://www.github.com/JustAKing12)

