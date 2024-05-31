package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    /**
     * Metodo para crear las tablas en la base de datos.
     * 
     * @param conexion para iniciar la conexion.
     */
    public void crearTablas(ConectarDB conexion) {
        
        try {
            Connection conn = conexion.getConnection();
            Statement statement = conn.createStatement(); 

            String sqlEquipo = "CREATE TABLE IF NOT EXISTS equipo ("
                    + "ID_equipo INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre VARCHAR(50) UNIQUE NOT NULL,"
                    + "ciudad VARCHAR(50),"
                    + "division VARCHAR(20),"
                    + "conferencia VARCHAR(20),"
                    + "estadio VARCHAR(40) UNIQUE,"
                    + "entrenador VARCHAR(50) UNIQUE);";

            final String SQL = "INSERT IGNORE INTO equipo (ID_equipo, nombre, ciudad, division, conferencia, estadio, entrenador) "
                    + "VALUES (1, 'Agencia Libre', '', '', '', '', '')";

            String sqlJugador = "CREATE TABLE IF NOT EXISTS jugador ("
                    + "ID_jugador INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "apellido VARCHAR(50) NOT NULL,"
                    + "posicion VARCHAR(10),"
                    + "altura DOUBLE,"
                    + "peso DOUBLE,"
                    + "fecha_nacimiento DATE,"
                    + "ID_equipo INT,"
                    + "CONSTRAINT FK_idequipo FOREIGN KEY(ID_equipo) REFERENCES equipo(ID_equipo));";

            String sqlTemporada = "CREATE TABLE IF NOT EXISTS temporada ("
                    + "ID_temporada INT AUTO_INCREMENT PRIMARY KEY,"
                    + "año VARCHAR(6) NOT NULL,"
                    + "tipo VARCHAR(40) NOT NULL,"
                    + "estado VARCHAR(20) NOT NULL,"
                    + "descripcion VARCHAR(255));";

            String sqlPartido = "CREATE TABLE IF NOT EXISTS partido ("
                    + "ID_partido INT AUTO_INCREMENT PRIMARY KEY,"
                    + "fecha DATE NOT NULL,"
                    + "equipo_local VARCHAR(50) NOT NULL,"
                    + "equipo_visitante VARCHAR(50) NOT NULL,"
                    + "puntuacion_local INT,"
                    + "puntuacion_visitante INT,"
                    + "ID_temporada INT NOT NULL,"
                    + "CONSTRAINT FK_idtemporada FOREIGN KEY(ID_temporada) REFERENCES temporada(ID_temporada));";

            String sqlEstadisticasJugador = "CREATE TABLE IF NOT EXISTS estadisticas_jugador ("
                    + "ID_estadisticas INT AUTO_INCREMENT PRIMARY KEY,"
                    + "puntos DOUBLE NOT NULL,"
                    + "rebotes DOUBLE NOT NULL,"
                    + "asistencias DOUBLE NOT NULL,"
                    + "robos DOUBLE NOT NULL,"
                    + "tapones DOUBLE NOT NULL,"
                    + "ID_jugador INT NOT NULL,"
                    + "ID_partido INT NOT NULL,"
                    + "CONSTRAINT FK_idjugador FOREIGN KEY(ID_jugador) REFERENCES jugador(ID_jugador),"
                    + "CONSTRAINT FK_idpartido FOREIGN KEY(ID_partido) REFERENCES partido(ID_partido));";

            String sqlEstadisticasEquipo = "CREATE TABLE IF NOT EXISTS estadisticas_equipo ("
                    + "ID_estadisticas INT AUTO_INCREMENT PRIMARY KEY,"
                    + "puntosAnotados INT NOT NULL,"
                    + "puntosEncajados INT NOT NULL,"
                    + "victorias INT,"
                    + "derrotas INT,"
                    + "registro VARCHAR(7),"
                    + "ID_equipo INT NOT NULL,"
                    + "ID_temporada INT NOT NULL,"
                    + "CONSTRAINT FK_idequipo2 FOREIGN KEY(ID_equipo) REFERENCES equipo(ID_equipo),"
                    + "CONSTRAINT FK_temporada1 FOREIGN KEY(ID_temporada) REFERENCES temporada(ID_temporada));";

            String sqlPremio = "CREATE TABLE IF NOT EXISTS premios ("
                    + "ID_premio INT AUTO_INCREMENT PRIMARY KEY,"
                    + "tipo_premio VARCHAR(50) NOT NULL,"
                    + "descripcion VARCHAR(255),"
                    + "votacion VARCHAR(50),"
                    + "categoria_premio VARCHAR(20),"
                    + "evento_premiacion VARCHAR(100),"
                    + "estatus_premio VARCHAR(20),"
                    + "ID_temporada INT NOT NULL,"
                    + "CONSTRAINT FK_temporada FOREIGN KEY(ID_temporada) REFERENCES temporada(ID_temporada));";

            statement.executeUpdate(sqlEquipo);
            statement.executeUpdate(SQL);
            statement.executeUpdate(sqlJugador);
            statement.executeUpdate(sqlTemporada);
            statement.executeUpdate(sqlPartido);
            statement.executeUpdate(sqlEstadisticasJugador);
            statement.executeUpdate(sqlEstadisticasEquipo);
            statement.executeUpdate(sqlPremio);

            System.out.println("Tablas de la base de datos creadas correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas de la base de datos: " + e.getMessage());
        }
    }
}
