package controlador;

import vista.formularios.FormularioEstadisticasE;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.EstadisticasEquipoPorTemporada;
import modelo.accesoADatos.EstadisticasEquipoDAO;
import vista.Vista;
import vista.VistaTablaUnica;

public class ControladorEstadisticasE implements Actualizar<EstadisticasEquipoPorTemporada> {
    private Vista vista;
    private VistaTablaUnica vista2;
    private EstadisticasEquipoDAO estadisticasEquipoDAO;

    public ControladorEstadisticasE(Vista vista, VistaTablaUnica vista2, EstadisticasEquipoDAO estadisticasEquipoDAO) {
        this.vista = vista;
        this.vista2 = vista2;
        vista2.setVisible(false);
        this.estadisticasEquipoDAO = estadisticasEquipoDAO;
    }

    @Override
    public List<EstadisticasEquipoPorTemporada> obtener() {
        return estadisticasEquipoDAO.obtenerTodos();
    }
    
    public void actualizarTablaEstadisticasE() {
        List<EstadisticasEquipoPorTemporada> estadisticas = estadisticasEquipoDAO.obtenerTodos();
        String[] columnas = {"ID", "Puntos Anotados", "Puntos Encajados", "Victorias", "Derrotas", "Registro", "ID Equipo", "ID Temporada"};
        Object[][] datos = estadisticas.stream().map(e -> new Object[]{
            e.getID_estadisticas(), e.getPuntosAnotados(), e.getPuntosEncajados(), e.getVictorias(),
            e.getDerrotas(), e.getRegistro(), e.getID_equipo(), e.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }
    
    private void actualizarTablaEstadisticasE(List<EstadisticasEquipoPorTemporada> estadisticas) {
        String[] columnas = {"ID", "Puntos Anotados", "Puntos Encajados", "Victorias", "Derrotas", "Registro", "ID Equipo", "ID Temporada"};
        Object[][] datos = estadisticas.stream().map(e -> new Object[]{
            e.getID_estadisticas(), e.getPuntosAnotados(), e.getPuntosEncajados(), e.getVictorias(),
            e.getDerrotas(), e.getRegistro(), e.getID_equipo(), e.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    } 

    private void actualizarTablaEquipos(List<String> equipos) {
        String[] columnas = {"Nombre del Equipo"};
        Object[][] datos = equipos.stream().map(e -> new Object[]{e}).toArray(Object[][]::new);
        actualizarTablaUnica(vista2, datos, columnas);
    }    
    
    public void agregarEstadisticasE() {
        EstadisticasEquipoPorTemporada nuevasEstadisticas = mostrarFormularioEstadisticasE(null);
        if (nuevasEstadisticas != null) {
            estadisticasEquipoDAO.insertar(nuevasEstadisticas);
            actualizarTablaEstadisticasE();
            JOptionPane.showMessageDialog(vista, "Estadisticas insertadas correctamente.");  
        }
    }

    public void eliminarEstadisticasE(int id) {
        estadisticasEquipoDAO.eliminar(id);
        actualizarTablaEstadisticasE();
    }

    public List<EstadisticasEquipoPorTemporada> buscarEstadisticasPorVictorias(Integer victorias) {
        List<EstadisticasEquipoPorTemporada> estadisticas = estadisticasEquipoDAO.obtenerEquiposPorVictorias(victorias);
        actualizarTablaEstadisticasE(estadisticas);
        return estadisticas;
    }

    public List<String> buscarEquiposPorPorcentajeVictorias(Integer idTemporada, Double porcentajeVictorias) {
        List<String> equipos = estadisticasEquipoDAO.obtenerEquiposPorPorcentajeVictorias(idTemporada, porcentajeVictorias);
        actualizarTablaEquipos(equipos);
        return equipos;
    }

    public void buscarEstadisticasEquipo(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Victorias" -> {
                String victorias = JOptionPane.showInputDialog(vista, "Ingrese el número de victorias:", 
                        "Búsqueda por Victorias", JOptionPane.PLAIN_MESSAGE);
                if (victorias != null && !victorias.isEmpty()) {
                    buscarEstadisticasPorVictorias(Integer.valueOf(victorias));
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número de victorias válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Porcentaje de Victorias" -> {

                String idTemporada = JOptionPane.showInputDialog(vista, "Ingrese el ID de la temporada:", 
                        "Búsqueda por Porcentaje de Victorias", JOptionPane.PLAIN_MESSAGE);
                String porcentajeVictorias = JOptionPane.showInputDialog(vista, "Ingrese el porcentaje de victorias (en decimal):", 
                        "Búsqueda por Porcentaje de Victorias", JOptionPane.PLAIN_MESSAGE);
                if (idTemporada != null && !idTemporada.isEmpty() && porcentajeVictorias != null && !porcentajeVictorias.isEmpty()) {
                    buscarEquiposPorPorcentajeVictorias(Integer.valueOf(idTemporada), Double.valueOf(porcentajeVictorias));
                    vista2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese valores válidos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }            
        }
    }     
    
    public void actualizarEstadisticas(Integer id) {
        EstadisticasEquipoPorTemporada stats = estadisticasEquipoDAO.obtenerPorId(id);
        if (stats != null) {
            EstadisticasEquipoPorTemporada statsActualizadas = mostrarFormularioEstadisticasE(stats);
            if (statsActualizadas != null) {
                estadisticasEquipoDAO.actualizar(statsActualizadas);
                actualizarTablaEstadisticasE();
            }
        } else {
            mostrarError("Las estadísticas del equipo no existen.");
        }
    }

    public void actualizarEstadisticasDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idStats = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarEstadisticas(idStats);
        } else {
            mostrarError("Por favor, seleccione un ID de estadísticas para actualizar.");
        }
    }    

    private EstadisticasEquipoPorTemporada mostrarFormularioEstadisticasE(EstadisticasEquipoPorTemporada estadisticas) {
        FormularioEstadisticasE formulario = new FormularioEstadisticasE();
        if (estadisticas != null) {
            formulario.setPuntosAnotados(String.valueOf(estadisticas.getPuntosAnotados()));
            formulario.setPuntosEncajados(String.valueOf(estadisticas.getPuntosEncajados()));
            formulario.setVictorias(String.valueOf(estadisticas.getVictorias()));
            formulario.setDerrotas(String.valueOf(estadisticas.getDerrotas()));
            formulario.setRegistro(estadisticas.getRegistro());
            formulario.setIDEquipo(String.valueOf(estadisticas.getID_equipo()));
            formulario.setIDTemporada(String.valueOf(estadisticas.getID_temporada()));
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");

        int result = JOptionPane.showConfirmDialog(null, formulario,
            estadisticas == null ? "Agregar Estadísticas" : "Actualizar Estadísticas", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {
                return new EstadisticasEquipoPorTemporada(
                    estadisticas != null ? estadisticas.getID_estadisticas() : 0,
                    Integer.valueOf(formulario.getPuntosAnotados()), Integer.valueOf(formulario.getPuntosEncajados()),
                    Integer.valueOf(formulario.getVictorias()), Integer.valueOf(formulario.getDerrotas()),
                    formulario.getRegistro(), Integer.valueOf(formulario.getIDEquipo()),
                    Integer.valueOf(formulario.getIDTemporada())
                );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
