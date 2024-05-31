package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Premio;

public class PremioDAO implements DAO<Premio> {
    private final Connection conn;

    public PremioDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Premio> obtenerTodos() {
        String query = "SELECT * FROM premios";
        return ejecutarConsultaLista(query);
    }

    @Override
    public Premio obtenerPorId(Integer id) {
        String query = "SELECT * FROM premios WHERE ID_premio = ?";
        return ejecutarConsultaUnica(query, id);
    }
    
    public List<Premio> obtenerPorTipo(String tipo) {
        String query = "SELECT * FROM premios WHERE tipo_premio = ?";
        return ejecutarConsultaLista(query, tipo);
    }
    
    public List<Premio> obtenerPorStatus(String status) {
        String query = "SELECT * FROM premios WHERE status = ?";
        return ejecutarConsultaLista(query, status);
    }

    @Override
    public void insertar(Premio premio) {
        String query = "INSERT INTO premios (tipoPremio, descripcion, votacion, categoria, statusPremio, ID_temporada) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        ejecutarActualizacion(query, premio);
    }

    @Override
    public void actualizar(Premio premio) {
        String query = "UPDATE premios SET tipoPremio = ?, "
                + "descripcion = ?, "
                + "votacion = ?, "
                + "categoria = ?, "
                + "statusPremio = ?, "
                + "ID_temporada = ? "
                + "WHERE ID_premio = ?";
        ejecutarActualizacion(query, premio);
        System.out.println("Premio actualizado correctamente.");
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM premios WHERE ID_premio = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Premio eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el premio: " + e.getMessage());
        }
    }

    private Premio convertirPremio(ResultSet rs) throws SQLException {
        return new Premio(
                rs.getInt("ID_premio"),
                rs.getString("tipoPremio"),
                rs.getString("descripcion"),
                rs.getString("votacion"),
                rs.getString("categoria"),
                rs.getString("statusPremio"),
                rs.getInt("ID_temporada")
        );
    }

    private void setPremioParametros(PreparedStatement pst, Premio premio) throws SQLException {
        pst.setString(1, premio.getTipoPremio());
        pst.setString(2, premio.getDescripcion());
        pst.setString(3, premio.getVotacion());
        pst.setString(4, premio.getCategoria());
        pst.setString(5, premio.getStatusPremio());
        pst.setInt(6, premio.getID_temporada());
        if (pst.getParameterMetaData().getParameterCount() == 7) {
            pst.setInt(7, premio.getID_premio());
        }
    }

    private List<Premio> ejecutarConsultaLista(String query, Object... params) {
        List<Premio> premios = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    premios.add(convertirPremio(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return premios;
    }

    private Premio ejecutarConsultaUnica(String query, Object... params) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return convertirPremio(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        return null;
    }

    private void ejecutarActualizacion(String query, Premio premio) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setPremioParametros(pst, premio);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
    }
}
