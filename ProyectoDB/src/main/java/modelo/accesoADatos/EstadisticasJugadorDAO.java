package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.EstadisticasJugadorPorPartido;

public class EstadisticasJugadorDAO implements DAO<EstadisticasJugadorPorPartido> {
    private final Connection conn;

    public EstadisticasJugadorDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<EstadisticasJugadorPorPartido> obtenerTodos() {
        String query = "SELECT * FROM estadisticas_jugador";
        return ejecutarConsultaLista(query);
    }

    @Override
    public EstadisticasJugadorPorPartido obtenerPorId(Integer id) {
        String query = "SELECT * FROM estadisticas_jugador WHERE ID_estadisticas = ?";
        return ejecutarConsultaUnica(query, id);
    }
    
    public List<EstadisticasJugadorPorPartido> obtenerPorIdPartido(Integer id) {
        String query = "SELECT * FROM estadisticas_jugador WHERE ID_partido = ?";
        return ejecutarConsultaLista(query, id);
    }
    
    public List<EstadisticasJugadorPorPartido> obtenerPorIdJugador(Integer id) {
        String query = "SELECT * FROM estadisticas_jugador WHERE ID_jugador = ?";
        return ejecutarConsultaLista(query, id);
    }

    @Override
    public void insertar(EstadisticasJugadorPorPartido stat) {
        String query = "INSERT INTO estadisticas_jugador (puntos, rebotes, asistencias, robos, tapones, ID_jugador, ID_partido) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, stat);      
    }

    @Override
    public void actualizar(EstadisticasJugadorPorPartido stat) {
        String query = "UPDATE estadisticas_jugador SET puntos = ?, "
                + "rebotes = ?, "
                + "asistencias = ?, "
                + "robos = ?, "
                + "tapones = ?, "
                + "ID_jugador = ?, "
                + "ID_partido = ? "
                + "WHERE ID_estadisticas = ?";
        ejecutarActualizacion(query, stat);
        JOptionPane.showMessageDialog(null, "Estadísticas actualizadas correctamente.");        
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM estadisticas_jugador WHERE ID_estadisticas = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Estadística eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar las estadísticas: " + e.getMessage());
        }
    }

    private EstadisticasJugadorPorPartido convertirStats(ResultSet rs) throws SQLException {
        return new EstadisticasJugadorPorPartido(
            rs.getInt("ID_estadisticas"),
            rs.getInt("puntos"),
            rs.getInt("rebotes"),
            rs.getInt("asistencias"),
            rs.getInt("robos"),
            rs.getInt("tapones"),
            rs.getInt("ID_jugador"),
            rs.getInt("ID_partido")
        );
    }

    private void setStatsParametros(PreparedStatement pst, EstadisticasJugadorPorPartido stat) throws SQLException {
        pst.setInt(1, stat.getPuntos());
        pst.setInt(2, stat.getRebotes());
        pst.setInt(3, stat.getAsistencias());
        pst.setInt(4, stat.getRobos());
        pst.setInt(5, stat.getTapones());
        pst.setInt(6, stat.getID_jugador());
        pst.setInt(7, stat.getID_partido());
        if (pst.getParameterMetaData().getParameterCount() == 8) {
            pst.setInt(8, stat.getID_estadisticas());
        }
    }

    private List<EstadisticasJugadorPorPartido> ejecutarConsultaLista(String query, Object... params) {
        List<EstadisticasJugadorPorPartido> stats = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    stats.add(convertirStats(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return stats;
    }

    private EstadisticasJugadorPorPartido ejecutarConsultaUnica(String query, Object... params) {
        EstadisticasJugadorPorPartido stat = null;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    stat = convertirStats(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return stat;
    }

    private void ejecutarActualizacion(String query, EstadisticasJugadorPorPartido stat) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setStatsParametros(pst, stat);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
    }
}
