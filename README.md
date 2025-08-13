# NBAdatabase

## Descripción
Este proyecto es una aplicación Java desarrollada para gestionar datos relacionados con la NBA, incluyendo equipos, jugadores, partidos, temporadas, estadísticas y premios. Utiliza un patrón MVC (Modelo-Vista-Controlador) y se conecta a una base de datos MySQL para almacenar y recuperar información.

El proyecto está construido con Maven y utiliza JDBC para la interacción con la base de datos.

## Estructura del Proyecto
- controlador/ : Contiene las clases controladoras que manejan la lógica de negocio (ej, ControladorEquipo.java, ControladorJugador.java).
- datosPrueba/ : Clases para datos de prueba (ej, Prueba.java).
- main/ : Punto de entrada principal (Main.java).
- modelo/ : Clases de modelo que representan las entidades (ej, Equipo.java, Jugador.java) y DAOs para acceso a datos (en el subpaquete accesoADatos).
- util/ : Utilidades como conexión a DB (ConectarDB.java), setup de base de datos (DatabaseSetup.java), y recursos como imágenes y el conector MySQL.
- vista/ : Clases de vista para la interfaz de usuario (ej, VistaPrincipal.java) y formularios (en el subpaquete formularios).

## Requisitos
- Java JDK 8 o superior
- Maven
- MySQL (via XAMPP)
- Configurar credenciales en credenciales.env (usuario, contraseña, URL de DB)

- ## Licencia
Proyecto final realizado en 1º de DAM para la asignatura de programación.
