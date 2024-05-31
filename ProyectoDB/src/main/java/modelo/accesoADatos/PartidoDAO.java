package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.Partido;

/**
 * Implementacion de la interfaz DAO para la clase Partido.
 */
public class PartidoDAO implements DAO<Partido> {
    private final Connection conn;

    public PartidoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Partido> obtenerTodos() {
        String query = "SELECT * FROM partido";
        return ejecutarConsultaLista(query);
    }

    @Override
    public Partido obtenerPorId(Integer id) {
        String query = "SELECT * FROM partido WHERE ID_partido = ?";
        return ejecutarConsultaUnica(query, id);
    }

    public List<Partido> obtenerPorEquipoLocal(String equipoLocal) {
        String query = "SELECT * FROM partido WHERE equipo_local = ?";
        return ejecutarConsultaLista(query, equipoLocal);
    }

    public List<Partido> obtenerPorEquipoVisitante(String equipoVisitante) {
        String query = "SELECT * FROM partido WHERE equipo_visitante = ?";
        return ejecutarConsultaLista(query, equipoVisitante);
    }
    
    public List<Partido> obtenerPorFecha(LocalDate fecha) {
        String query = "SELECT * FROM partido WHERE fecha = ?";
        return ejecutarConsultaLista(query, fecha);
    }

    @Override
    public void insertar(Partido partido) {
        String query = "INSERT INTO partido (fecha, equipo_local, equipo_visitante, puntuacion_local, puntuacion_visitante, ID_temporada) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, partido);
    }

    @Override
    public void actualizar(Partido partido) {
        String query = "UPDATE partido SET fecha = ?, "
                + "equipo_local = ?, "
                + "equipo_visitante = ?, "
                + "puntuacion_local = ?, "
                + "puntuacion_visitante = ?, "
                + "ID_temporada = ? "
                + "WHERE ID_partido = ?";
        ejecutarActualizacion(query, partido);
        JOptionPane.showMessageDialog(null, "Partido actualizado correctamente.");
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM partido WHERE ID_partido = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Partido eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el partido: " + e.getMessage());
        }
    }

    private Partido convertirPartido(ResultSet rs) throws SQLException {
        return new Partido(
            rs.getInt("ID_partido"),
            rs.getObject("fecha", LocalDate.class),
            obtenerEquipoPorNombre(rs.getString("equipo_local")),
            obtenerEquipoPorNombre(rs.getString("equipo_visitante")),
            rs.getInt("puntuacion_local"),
            rs.getInt("puntuacion_visitante"),
            rs.getInt("ID_temporada")
        );
    }

    private void setPartidoParametros(PreparedStatement pst, Partido partido) throws SQLException {
        pst.setObject(1, partido.getFechaPartido());
        pst.setString(2, partido.getEquipoLocal().getNombre());
        pst.setString(3, partido.getEquipoVisitante().getNombre());
        pst.setInt(4, partido.getPuntuacionLocal());
        pst.setInt(5, partido.getPuntuacionVisitante());
        pst.setInt(6, partido.getID_temporada());
        if (pst.getParameterMetaData().getParameterCount() == 7) {
            pst.setInt(7, partido.getID_partido());
        }
    }

    private List<Partido> ejecutarConsultaLista(String query, Object... params) {
        List<Partido> partidos = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    partidos.add(convertirPartido(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return partidos;
    }

    private Partido ejecutarConsultaUnica(String query, Object... params) {
        Partido partido = null;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    partido = convertirPartido(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return partido;
    }

    private void ejecutarActualizacion(String query, Partido partido) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setPartidoParametros(pst, partido);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
    }

    private Equipo obtenerEquipoPorNombre(String nombreEquipo) {
        EquipoDAO equipoDAO = new EquipoDAO(conn);
        return equipoDAO.obtenerPorNombre(nombreEquipo);
    }
}
