package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Jugador;

/**
 * Implementacion de la interfaz DAO para la clase Jugador.
 */
public class JugadorDAO implements DAO<Jugador> {
    private final Connection conn;

    public JugadorDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Jugador> obtenerTodos() {
        String query = "SELECT * FROM jugador";
        return ejecutarConsultaLista(query);
    }

    @Override
    public Jugador obtenerPorId(Integer id) {
        String query = "SELECT * FROM jugador WHERE ID_jugador = ?";
        return ejecutarConsultaUnica(query, id);
    }

    public List<Jugador> obtenerPorApellidos(String apellido) {
        String query = "SELECT * FROM jugador WHERE apellido = ?";
        return ejecutarConsultaLista(query, apellido);
    }

    public List<Jugador> obtenerPorPosicion(String posicion) {
        String query = "SELECT * FROM jugador WHERE posicion = ?";
        return ejecutarConsultaLista(query, posicion);
    }
    
    public List<Jugador> obtenerPorNombreEquipo(String nombreEquipo) {
        String query = "SELECT jugador.* FROM jugador "
                + "JOIN equipo ON jugador.ID_equipo = equipo.ID_equipo "
                + "WHERE equipo.nombre LIKE ?";
        return ejecutarConsultaLista(query, "%" + nombreEquipo + "%");
    }  

    @Override
    public void insertar(Jugador jugador) {
        String query = "INSERT INTO jugador (nombre, apellido, posicion, altura, peso, fecha_nacimiento, ID_equipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, jugador);
    }

    @Override
    public void actualizar(Jugador jugador) {
        String query = "UPDATE jugador SET nombre = ?, "
                + "apellido = ?, "
                + "posicion = ?, "
                + "altura = ?, "
                + "peso = ?, "
                + "fecha_nacimiento = ?, "
                + "ID_equipo = ? "
                + "WHERE ID_jugador = ?";
        ejecutarActualizacion(query, jugador);
        JOptionPane.showMessageDialog(null, "Jugador actualizado correctamente.");
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM jugador WHERE ID_jugador = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Jugador eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el jugador: " + e.getMessage());
        }
    }

    private Jugador convertirJugador(ResultSet rs) throws SQLException {
        return new Jugador(
            rs.getInt("ID_jugador"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("posicion"),
            rs.getDouble("altura"),
            rs.getDouble("peso"),
            rs.getObject("fecha_nacimiento", LocalDate.class),
            rs.getInt("ID_equipo")
        );
    }

    private void setJugadorParametros(PreparedStatement pst, Jugador jugador) throws SQLException {
        pst.setString(1, jugador.getNombre());
        pst.setString(2, jugador.getApellido());
        pst.setString(3, jugador.getPosicion());
        pst.setDouble(4, jugador.getAltura());
        pst.setDouble(5, jugador.getPeso());
        pst.setObject(6, jugador.getFechaNacimiento());
        pst.setInt(7, jugador.getId_equipo());
        if (pst.getParameterMetaData().getParameterCount() == 8) {
            pst.setInt(8, jugador.getID_jugador());
        }
    }

    private List<Jugador> ejecutarConsultaLista(String query, Object... params) {
        List<Jugador> jugadores = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    jugadores.add(convertirJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return jugadores;
    }

    private Jugador ejecutarConsultaUnica(String query, Object... params) {
        Jugador jugador = null;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    jugador = convertirJugador(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return jugador;
    }

    private void ejecutarActualizacion(String query, Jugador jugador) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setJugadorParametros(pst, jugador);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
    }
}
