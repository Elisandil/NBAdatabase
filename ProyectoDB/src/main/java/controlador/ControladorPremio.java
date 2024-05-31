package controlador;

import vista.formularios.FormularioPremio;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Premio;
import modelo.accesoADatos.PremioDAO;
import vista.Vista;

public class ControladorPremio implements Actualizar<Premio> {
    private Vista vista;
    private PremioDAO premioDAO;

    public ControladorPremio(Vista vista, PremioDAO premioDAO) {
        this.vista = vista;
        this.premioDAO = premioDAO;
    }

    @Override
    public List<Premio> obtener() {
        return premioDAO.obtenerTodos();
    }

    public void actualizarTablaPremios() {
        List<Premio> premios = premioDAO.obtenerTodos();
        String[] columnas = {"ID", "Tipo", "Descripción", "Votación", "Categoría", "Status", "ID Temporada"};
        Object[][] datos = premios.stream().map(p -> new Object[]{
                p.getID_premio(), p.getTipoPremio(), p.getDescripcion(),
                p.getVotacion(), p.getCategoria(), p.getStatusPremio(),
                p.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }

    private void actualizarTablaPremios(List<Premio> premios) {
        String[] columnas = {"ID", "Tipo", "Descripción", "Votación", "Categoría", "Status", "ID Temporada"};
        Object[][] datos = premios.stream().map(p -> new Object[]{
                p.getID_premio(), p.getTipoPremio(), p.getDescripcion(),
                p.getVotacion(), p.getCategoria(), p.getStatusPremio(),
                p.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }

    public void agregarPremio() {
        Premio nuevoPremio = mostrarFormularioPremio(null);
        if (nuevoPremio != null) {
            premioDAO.insertar(nuevoPremio);
            actualizarTablaPremios();
            JOptionPane.showMessageDialog(vista, "Premio insertado correctamente.");            
        }
    }

    public void eliminarPremio(int id) {
        premioDAO.eliminar(id);
        actualizarTablaPremios();
    }

    public List<Premio> buscarPremioPorTipo(String tipo) {
        List<Premio> premios = premioDAO.obtenerPorTipo(tipo);
        actualizarTablaPremios(premios);
        return premios;
    }

    public List<Premio> buscarPremioPorStatus(String status) {
        List<Premio> premios = premioDAO.obtenerPorStatus(status);
        actualizarTablaPremios(premios);
        return premios;
    }
    
    public void buscarPremios(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Tipo" -> {
                String tipoPremio = JOptionPane.showInputDialog(vista, "Ingrese el tipo de premio:", 
                        "Búsqueda por Tipo", JOptionPane.PLAIN_MESSAGE);
                if (tipoPremio != null && !tipoPremio.isEmpty()) {
                    buscarPremioPorTipo(tipoPremio);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un tipo válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Status" -> {
                String statusPremio = JOptionPane.showInputDialog(vista, "Ingrese el status del premio:", 
                        "Búsqueda por Status", JOptionPane.PLAIN_MESSAGE);
                if (statusPremio != null && !statusPremio.isEmpty()) {
                    buscarPremioPorStatus(statusPremio);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un status válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }      

    public void actualizarPremio(Integer id) {
        Premio premio = premioDAO.obtenerPorId(id);
        if (premio != null) {
            Premio premioActualizado = mostrarFormularioPremio(premio);
            if (premioActualizado != null) {
                premioDAO.actualizar(premioActualizado);
                actualizarTablaPremios();
            }
        } else {
            mostrarError("El premio seleccionado no existe.");
        }
    }

    public void actualizarPremioDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idPremio = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarPremio(idPremio);
        } else {
            mostrarError("Por favor, seleccione un premio para actualizar.");
        }
    }

    private Premio mostrarFormularioPremio(Premio premio) {
        FormularioPremio formulario = new FormularioPremio();
        if (premio != null) {
            formulario.setTipo(premio.getTipoPremio());
            formulario.setDescripcion(premio.getDescripcion());
            formulario.setVotacion(premio.getVotacion());
            formulario.setCategoria(premio.getCategoria());
            formulario.setStatus(premio.getStatusPremio());
            formulario.setTemporadaId(premio.getID_temporada());
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");
        int result = JOptionPane.showConfirmDialog(null, formulario, 
            premio == null ? "Agregar Premio" : "Actualizar Premio", JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {
            return new Premio(
                premio != null ? premio.getID_premio() : 0,
                formulario.getTipo(), formulario.getDescripcion(),
                formulario.getVotacion(), formulario.getCategoria(),
                formulario.getStatus(), formulario.getTemporadaId()
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
