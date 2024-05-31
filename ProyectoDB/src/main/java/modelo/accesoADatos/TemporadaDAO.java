package modelo.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Temporada;

public class TemporadaDAO implements DAO<Temporada> {
    private Connection conn;

    public TemporadaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Temporada> obtenerTodos() {
        String query = "SELECT * FROM temporada";
        return ejecutarConsultaLista(query);
    }

    @Override
    public Temporada obtenerPorId(Integer id) {
        String query = "SELECT * FROM temporada WHERE ID_temporada = ?";
        return ejecutarConsultaUnica(query, id);
    }
    
    public List<Temporada> obtenerPorAnio(Integer anio) { 
        String query = "SELECT * FROM temporada WHERE año = ?";
        return ejecutarConsultaLista(query, anio);
    }

    public List<Temporada> obtenerPorTipo(String tipo) {
        String query = "SELECT * FROM temporada WHERE tipo = ?";
        return ejecutarConsultaLista(query, tipo);
    }
    
    @Override
    public void insertar(Temporada temporada) {
        String query = "INSERT INTO temporada(año, tipo, estado, descripcion) "
                + "VALUES(?, ?, ?, ?)";
        ejecutarActualizaion(query, temporada);
    }

    @Override
    public void actualizar(Temporada temporada) {
        String query = "UPDATE temporada SET "
                + "año = ?, "
                + "tipo = ?, "
                + "estado = ?, "
                + "descripcion = ? "
                + "WHERE ID_temporada = ?";
        ejecutarActualizaion(query, temporada);
        JOptionPane.showMessageDialog(null, "Temporada actualizada correctamente.");        
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM temporada WHERE ID_temporada = ?";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Temporada borrada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al borrar la temporada: " + e.getMessage());
        }
    }
    
    private Temporada convertirTemporada(ResultSet rs) throws SQLException {       
        return new Temporada(rs.getInt("ID_temporada"), 
                rs.getInt("año"),
                rs.getString("tipo"), 
                rs.getString("estado"), 
                rs.getString("descripcion"));
    }
    
    private void setTemporadaParametros(PreparedStatement pst, Temporada temporada) throws SQLException {
        pst.setInt(1, temporada.getAnio());
        pst.setString(2, temporada.getTipo());
        pst.setString(3, temporada.getEstado());
        pst.setString(4, temporada.getDescripcion());
        if (pst.getParameterMetaData().getParameterCount() == 5) {
            pst.setInt(5, temporada.getId_temporada());
        }
    }
    
    private List<Temporada> ejecutarConsultaLista(String query, Object... params) {
        List<Temporada> temporadas = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while(rs.next()) {
                    temporadas.add(convertirTemporada(rs));
                }                
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return temporadas;
    }
    
    private Temporada ejecutarConsultaUnica(String query, Object... params) {
        Temporada temporada = null;
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    temporada = convertirTemporada(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return temporada;
    }
    
    private void ejecutarActualizaion(String query, Temporada temporada) {
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setTemporadaParametros(pst, temporada);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualizacion: " + e.getMessage());
        }
    }
}
