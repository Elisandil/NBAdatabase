package controlador;

import vista.formularios.FormularioTemporada;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Temporada;
import modelo.accesoADatos.TemporadaDAO;
import vista.Vista;

public class ControladorTemporada implements Actualizar<Temporada> {
    private Vista vista;
    private TemporadaDAO temporadaDAO;

    public ControladorTemporada(Vista vista, TemporadaDAO temporadaDAO) {
        this.vista = vista;
        this.temporadaDAO = temporadaDAO;
    }

    @Override
    public List<Temporada> obtener() {
        return temporadaDAO.obtenerTodos();
    }

    public void actualizarTablaTemporada() {
        List<Temporada> temporadas = obtener();
        String[] temporadaNombreColumnas = {"ID", "año", "tipo", "estado", "descripcion"};
        Object[][] temporadaDatos = new Object[temporadas.size()][temporadaNombreColumnas.length];
        for (int i = 0; i < temporadas.size(); i++) {
            Temporada temporada = temporadas.get(i);
            temporadaDatos[i][0] = temporada.getId_temporada();
            temporadaDatos[i][1] = temporada.getAnio();
            temporadaDatos[i][2] = temporada.getTipo();
            temporadaDatos[i][3] = temporada.getEstado();
            temporadaDatos[i][4] = temporada.getDescripcion();            
        }
        actualizarTabla(vista, temporadaDatos, temporadaNombreColumnas);
    }
    
    public void actualizarTablaTemporada(List<Temporada> temporadas) {
        String[] temporadaNombreColumnas = {"ID", "año", "tipo", "estado", "descripcion"};
        Object[][] temporadaDatos = new Object[temporadas.size()][temporadaNombreColumnas.length];
        for (int i = 0; i < temporadas.size(); i++) {
            Temporada temporada = temporadas.get(i);
            temporadaDatos[i][0] = temporada.getId_temporada();
            temporadaDatos[i][1] = temporada.getAnio();
            temporadaDatos[i][2] = temporada.getTipo();
            temporadaDatos[i][3] = temporada.getEstado();
            temporadaDatos[i][4] = temporada.getDescripcion();            
        }
        actualizarTabla(vista, temporadaDatos, temporadaNombreColumnas);
    }    

    public void agregarTemporada() {
        Temporada nuevaTemporada = mostrarFormularioTemporada(null);
        if (nuevaTemporada != null) {
            temporadaDAO.insertar(nuevaTemporada);
            actualizarTablaTemporada();
            JOptionPane.showMessageDialog(vista, "Temporada insertada correctamente.");
        }
    }

    public void eliminarTemporada(int id) {
        temporadaDAO.eliminar(id);
        actualizarTablaTemporada();
    }

    public List<Temporada> buscarTemporadaPorAnio(Integer anio) {
        List<Temporada> temporadas = temporadaDAO.obtenerPorAnio(anio);
        actualizarTablaTemporada(temporadas);
        return temporadas;
    }

    public List<Temporada> buscarTemporadaPorTipo(String tipo) {
        List<Temporada> temporadas = temporadaDAO.obtenerPorTipo(tipo);
        actualizarTablaTemporada(temporadas);
        return temporadas;
    }
    
    public void buscarTemporadas(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Año" -> {
                String anioTemporada = JOptionPane.showInputDialog(vista, "Ingrese el anio de la temporada:", 
                        "Búsqueda por Año", JOptionPane.PLAIN_MESSAGE);
                if (anioTemporada != null && !anioTemporada.isEmpty()) {
                    buscarTemporadaPorAnio(Integer.valueOf(anioTemporada));
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un anio válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Tipo" -> {
                String tipoTemporada = JOptionPane.showInputDialog(vista, "Ingrese el tipo de la temporada:", 
                        "Búsqueda por Tipo", JOptionPane.PLAIN_MESSAGE);
                if (tipoTemporada != null && !tipoTemporada.isEmpty()) {
                    buscarTemporadaPorTipo(tipoTemporada);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un tipo válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }    

    public void actualizarTemporada(Integer id) {
        Temporada temporada = temporadaDAO.obtenerPorId(id);
        if (temporada != null) {
            Temporada temporadaActualizada = mostrarFormularioTemporada(temporada);
            if (temporadaActualizada != null) {
                temporadaDAO.actualizar(temporadaActualizada);
                actualizarTablaTemporada();
            }
        } else {
            mostrarError("La temporada no existe.");
        }
    }

    public void actualizarTemporadaDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idTemporada = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarTemporada(idTemporada);
        } else {
            mostrarError("Por favor, seleccione una temporada para actualizar.");
        }
    }    
    
    private Temporada mostrarFormularioTemporada(Temporada temporada) {
        FormularioTemporada formulario = new FormularioTemporada();
        if (temporada != null) {
            formulario.setAnio(temporada.getAnio());
            formulario.setTipo(temporada.getTipo());
            formulario.setEstado(temporada.getEstado());
            formulario.setDescripcion(temporada.getDescripcion());
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");
        
        int result = JOptionPane.showConfirmDialog(null, formulario, 
            temporada == null ? "Agregar Temporada" : "Actualizar Temporada", JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {
            return new Temporada(
                temporada != null ? temporada.getId_temporada() : 0,
                formulario.getAnio(),
                formulario.getTipo(),
                formulario.getEstado(),
                formulario.getDescripcion()
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
