package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.EstadisticasEquipoPorTemporada;

public class EstadisticasEquipoDAO implements DAO<EstadisticasEquipoPorTemporada> {
    private Connection conn;

    public EstadisticasEquipoDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<EstadisticasEquipoPorTemporada> obtenerTodos() {
        String query = "SELECT * FROM estadisticas_equipo";
        return ejecutarConsultaLista(query);
    }

    @Override
    public EstadisticasEquipoPorTemporada obtenerPorId(Integer id) {
        String query = "SELECT * FROM estadisticas_equipo WHERE ID_estadisticas = ?";
        return ejecutarConsultaUnica(query, id);
    }
    
    public List<EstadisticasEquipoPorTemporada> obtenerEquiposPorVictorias(Integer victorias) {
        String query = "SELECT * FROM estadisticas_equipo WHERE victorias = ?";
        return ejecutarConsultaLista(query, victorias);
    }
    
    public List<String> obtenerEquiposPorPorcentajeVictorias(Integer idTemporada, Double porcentajeVictorias) {
        String query = "SELECT equipo.nombre FROM estadisticas_equipo " +
                       "INNER JOIN equipo ON estadisticas_equipo.ID_equipo = equipo.ID_equipo " +
                       "WHERE estadisticas_equipo.ID_temporada = ? " +
                       "AND (estadisticas_equipo.victorias / (estadisticas_equipo.victorias + estadisticas_equipo.derrotas)) >= ?";
        return ejecutarConsultaNombresEquipos(query, idTemporada, porcentajeVictorias);
    }
    
    @Override
    public void insertar(EstadisticasEquipoPorTemporada stat) {
        String query = "INSERT INTO estadisticas_equipo (puntosAnotados, puntosEncajados, victorias, derrotas, registro, ID_equipo, ID_temporada)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, stat);      
    }

    @Override
    public void actualizar(EstadisticasEquipoPorTemporada stat) {
        String query = "UPDATE estadisticas_equipo SET puntosAnotados = ?, "
                + "puntosEncajados = ?, "
                + "victorias = ?, "
                + "derrotas = ?, "
                + "registro = ?, "
                + "ID_equipo = ?, "
                + "ID_temporada = ? "
                + "WHERE ID_estadisticas = ?";
        ejecutarActualizacion(query, stat);
        JOptionPane.showMessageDialog(null, "Estadisticas actualizadas correctamente.");        
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM estadisticas_equipo WHERE ID_estadisticas = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estadisticas borradas correctamente.");            
        } catch (SQLException e) {
            System.err.println("Error al eliminar las estadísticas: " + e.getMessage());
        }
    }
    
    private EstadisticasEquipoPorTemporada convertirStats(ResultSet rs) throws SQLException {
        return new EstadisticasEquipoPorTemporada(rs.getInt("ID_estadisticas"), 
                rs.getInt("puntosAnotados"), 
                rs.getInt("puntosEncajados"),
                rs.getInt("victorias"),
                rs.getInt("derrotas"),
                rs.getString("registro"), 
                rs.getInt("ID_equipo"), 
                rs.getInt("ID_temporada"));
    }
    
    private void setStatsParametros(PreparedStatement pst, EstadisticasEquipoPorTemporada stat) throws SQLException {
        pst.setInt(1, stat.getPuntosAnotados());
        pst.setInt(2, stat.getPuntosEncajados());
        pst.setInt(3, stat.getVictorias());
        pst.setInt(4, stat.getDerrotas());
        pst.setString(5, stat.getRegistro());
        pst.setInt(6, stat.getID_equipo());
        pst.setInt(7, stat.getID_temporada());
        if (pst.getParameterMetaData().getParameterCount() == 8) {
            pst.setInt(8, stat.getID_estadisticas());
        }
    }
    
    private List<EstadisticasEquipoPorTemporada> ejecutarConsultaLista(String query, Object... params) {
        List<EstadisticasEquipoPorTemporada> stats = new ArrayList<>();
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
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return stats;
    }
    
    private List<String> ejecutarConsultaNombresEquipos(String query, Object... params) {
        List<String> nombresEquipos = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    nombresEquipos.add(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return nombresEquipos;
    }  
    
    private EstadisticasEquipoPorTemporada ejecutarConsultaUnica(String query, Object... params) {
        EstadisticasEquipoPorTemporada stat = null;
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
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());            
        }
        return stat;
    }
    
    private void ejecutarActualizacion(String query, EstadisticasEquipoPorTemporada stat) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setStatsParametros(pst, stat);
            pst.executeLargeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());            
        }
    }
}
