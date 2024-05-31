package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Equipo;


/**
 * Implementacion de la interfaz DAO para la clase Equipo.
 */
public class EquipoDAO implements DAO<Equipo> {
    private final Connection conn;

    public EquipoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Equipo> obtenerTodos() {
        String query = "SELECT * FROM equipo";
        return ejecutarConsultaLista(query);
    }

    @Override
    public Equipo obtenerPorId(Integer id) {
        String query = "SELECT * FROM equipo WHERE ID_equipo = ?";
        return ejecutarConsultaUnica(query, id);
    }

    public Equipo obtenerPorNombre(String nombre) {
        String query = "SELECT * FROM equipo WHERE nombre = ?";
        return ejecutarConsultaUnica(query, nombre);
    }

    public List<Equipo> obtenerPorCiudad(String ciudad) {
        String query = "SELECT * FROM equipo WHERE ciudad = ?";
        return ejecutarConsultaLista(query, ciudad);
    }
    
    public List<Equipo> obtenerPorDivision(String division) {
        String query = "SELECT * FROM equipo WHERE division = ?";
        return ejecutarConsultaLista(query, division);
    }

    public List<Equipo> obtenerPorConferencia(String conferencia) {
        String query = "SELECT * FROM equipo WHERE conferencia = ?";
        return ejecutarConsultaLista(query, conferencia);
    }    
    
    public Equipo obtenerPorEstadio(String estadio) {
        String query = "SELECT * FROM equipo WHERE estadio = ?";
        return ejecutarConsultaUnica(query, estadio);
    }

    public Equipo obtenerPorEntrenador(String entrenador) {
        String query = "SELECT * FROM equipo WHERE entrenador = ?";
        return ejecutarConsultaUnica(query, entrenador);
    }    

    @Override
    public void insertar(Equipo equipo) {
        String query = "INSERT INTO equipo (nombre, ciudad, division, conferencia, estadio, entrenador) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, equipo);
    }

    @Override
    public void actualizar(Equipo equipo) {
        String query = "UPDATE equipo SET nombre = ?, "
                + "ciudad = ?, "
                + "division = ?, "
                + "conferencia = ?, "
                + "estadio = ?, "
                + "entrenador = ? "
                + "WHERE ID_equipo = ?";
        ejecutarActualizacion(query, equipo);
        JOptionPane.showMessageDialog(null, "Equipo actualizado correctamente.");
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM equipo WHERE ID_equipo = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Equipo eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el equipo: " + e.getMessage());
        }
    }   

    private Equipo convertirEquipo(ResultSet rs) throws SQLException {
        return new Equipo(
            rs.getInt("ID_equipo"),
            rs.getString("nombre"),
            rs.getString("ciudad"),
            rs.getString("division"),
            rs.getString("conferencia"),
            rs.getString("estadio"),
            rs.getString("entrenador")
        );
    }

    private void setEquipoParametros(PreparedStatement pst, Equipo equipo) throws SQLException {
        pst.setString(1, equipo.getNombre());
        pst.setString(2, equipo.getCiudad());
        pst.setString(3, equipo.getDivision());
        pst.setString(4, equipo.getConferencia());
        pst.setString(5, equipo.getEstadio());
        pst.setString(6, equipo.getEntrenador());
        if (pst.getParameterMetaData().getParameterCount() == 7) {
            pst.setInt(7, equipo.getID_equipo());
        }
    }

    private List<Equipo> ejecutarConsultaLista(String query, Object... params) {
        List<Equipo> equipos = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    equipos.add(convertirEquipo(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return equipos;
    }

    private Equipo ejecutarConsultaUnica(String query, Object... params) {
        Equipo equipo = null;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    equipo = convertirEquipo(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return equipo;
    }

    private void ejecutarActualizacion(String query, Equipo equipo) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setEquipoParametros(pst, equipo);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
    }    
}
