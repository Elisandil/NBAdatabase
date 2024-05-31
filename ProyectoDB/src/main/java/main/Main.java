package main;

import datosPrueba.Prueba;
import static datosPrueba.Prueba.generarEstadisticasEquipos;
import controlador.ControladorPrincipal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.Equipo;
import modelo.EstadisticasEquipoPorTemporada;
import modelo.Jugador;
import modelo.Partido;
import modelo.Temporada;
import modelo.accesoADatos.EquipoDAO;
import modelo.accesoADatos.EstadisticasEquipoDAO;
import modelo.accesoADatos.EstadisticasJugadorDAO;
import modelo.accesoADatos.JugadorDAO;
import modelo.accesoADatos.PartidoDAO;
import modelo.accesoADatos.PremioDAO;
import modelo.accesoADatos.TemporadaDAO;
import util.ConectarDB;
import util.DatabaseSetup;
import vista.VistaPrincipal;

public class Main {
    
    public static void main(String[] args) {
//        DatabaseSetup setup = new DatabaseSetup();   
//        String configFile = "src/main/java/util/credenciales.env";
        ConectarDB conexion = null;
        Connection conn = null;

        try {
            conexion = new ConectarDB();
            conn = conexion.getConnection();
//            setup.crearTablas(conexion);            
            VistaPrincipal vista = new VistaPrincipal();        
            EquipoDAO equipoDAO = new EquipoDAO(conn);
            List<Equipo> equipos = Prueba.generarEquipos();

            for (Equipo equipo : equipos) {
                equipoDAO.insertar(equipo);
            }
            TemporadaDAO temporadaDAO = new TemporadaDAO(conn);
            List<Temporada> temporadas = Prueba.generarTemporadas();

            for (Temporada temporada : temporadas) {
                temporadaDAO.insertar(temporada);
            }                        
            PartidoDAO partidoDAO = new PartidoDAO(conn);
            List<Partido> partidos = Prueba.generarPartidos(equipos, temporadas);
            
            for (Partido partido : partidos) {
                partidoDAO.insertar(partido);
            }
            JugadorDAO jugadorDAO = new JugadorDAO(conn);            
            List<Jugador> jugadores = Prueba.generarJugadores(equipos);
            
            for (Jugador jugador : jugadores) {
                jugadorDAO.insertar(jugador);
            }
            EstadisticasJugadorDAO estadisticasDAO = new EstadisticasJugadorDAO(conn);
            EstadisticasEquipoDAO estadisticasEDAO = new EstadisticasEquipoDAO(conn);
            List<EstadisticasEquipoPorTemporada> estadisticasEquipos = generarEstadisticasEquipos();

            for (EstadisticasEquipoPorTemporada estadisticas : estadisticasEquipos) {
                estadisticasEDAO.insertar(estadisticas);
            }            
            PremioDAO premioDAO = new PremioDAO(conn);
            
            ControladorPrincipal c1 = new ControladorPrincipal(vista, equipoDAO, partidoDAO, 
                    jugadorDAO, temporadaDAO, estadisticasDAO, estadisticasEDAO, premioDAO);

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
//            if (conexion != null) {
//                conexion.closeConnection();
//            }
        }
    }
}
