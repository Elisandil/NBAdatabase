package controlador;

import vista.formularios.FormularioEstadisticasJ;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.EstadisticasJugadorPorPartido;
import modelo.accesoADatos.EstadisticasJugadorDAO;
import vista.Vista;

public class ControladorEstadisticasJ implements Actualizar {
    private Vista vista;
    private EstadisticasJugadorDAO estadisticasJugadorDAO;

    public ControladorEstadisticasJ(Vista vista, EstadisticasJugadorDAO estadisticasJugadorDAO) {
        this.vista = vista;
        this.estadisticasJugadorDAO = estadisticasJugadorDAO;
    }

    @Override
    public List<EstadisticasJugadorPorPartido> obtener() {
        return estadisticasJugadorDAO.obtenerTodos();
    }

    public void actualizarTablaEstadisticasJ() {
        List<EstadisticasJugadorPorPartido> estadisticas = estadisticasJugadorDAO.obtenerTodos();
        String[] columnas = {"ID", "Puntos", "Rebotes", "Asistencias", "Robos", "Tapones", "ID Jugador", "ID Partido"};
        Object[][] datos = estadisticas.stream().map(e -> new Object[]{
            e.getID_estadisticas(), e.getPuntos(), e.getRebotes(), e.getAsistencias(),
            e.getRobos(), e.getTapones(), e.getID_jugador(), e.getID_partido()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }

    private void actualizarTablaEstadisticasJ(List<EstadisticasJugadorPorPartido> estadisticas) {
        String[] columnas = {"ID", "Puntos", "Rebotes", "Asistencias", "Robos", "Tapones", "ID Jugador", "ID Partido"};
        Object[][] datos = estadisticas.stream().map(e -> new Object[]{
            e.getID_estadisticas(), e.getPuntos(), e.getRebotes(), e.getAsistencias(),
            e.getRobos(), e.getTapones(), e.getID_jugador(), e.getID_partido()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }

    public void agregarEstadisticasJ() {
        EstadisticasJugadorPorPartido nuevasEstadisticas = mostrarFormularioEstadisticasJ(null);
        if (nuevasEstadisticas != null) {
            estadisticasJugadorDAO.insertar(nuevasEstadisticas);
            actualizarTablaEstadisticasJ();
            JOptionPane.showMessageDialog(vista, "Estadísticas insertadas correctamente.");
        }
    }

    public void eliminarEstadisticasJ(int id) {
        estadisticasJugadorDAO.eliminar(id);
        actualizarTablaEstadisticasJ();
    }

    public List<EstadisticasJugadorPorPartido> buscarEstadisticasPorIdPartido(Integer idPartido) {
        List<EstadisticasJugadorPorPartido> estadisticas = estadisticasJugadorDAO.obtenerPorIdPartido(idPartido);
        actualizarTablaEstadisticasJ(estadisticas);
        return estadisticas;
    }

    public List<EstadisticasJugadorPorPartido> buscarEstadisticasPorIdJugador(Integer idJugador) {
        List<EstadisticasJugadorPorPartido> estadisticas = estadisticasJugadorDAO.obtenerPorIdJugador(idJugador);
        actualizarTablaEstadisticasJ(estadisticas);
        return estadisticas;
    }

    public void buscarEstadisticasJugador(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por ID de Partido" -> {
                String idPartido = JOptionPane.showInputDialog(vista, "Ingrese el ID de Partido:",
                        "Búsqueda por ID de Partido", JOptionPane.PLAIN_MESSAGE);
                if (idPartido != null && !idPartido.isEmpty()) {
                    buscarEstadisticasPorIdPartido(Integer.valueOf(idPartido));
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un partido válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por ID de Jugador" -> {
                String idJugador = JOptionPane.showInputDialog(vista, "Ingrese el ID de Jugador:",
                        "Búsqueda por ID de Jugador", JOptionPane.PLAIN_MESSAGE);
                if (idJugador != null && !idJugador.isEmpty()) {
                    buscarEstadisticasPorIdJugador(Integer.valueOf(idJugador));
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un jugador válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void actualizarEstadisticas(Integer id) {
        EstadisticasJugadorPorPartido stats = estadisticasJugadorDAO.obtenerPorId(id);
        if (stats != null) {
            EstadisticasJugadorPorPartido statsActualizadas = mostrarFormularioEstadisticasJ(stats);
            if (statsActualizadas != null) {
                estadisticasJugadorDAO.actualizar(statsActualizadas);
                actualizarTablaEstadisticasJ();
            }
        } else {
            mostrarError("Las estadísticas del jugador no existen.");
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

    private EstadisticasJugadorPorPartido mostrarFormularioEstadisticasJ(EstadisticasJugadorPorPartido estadisticas) {
        FormularioEstadisticasJ formulario = new FormularioEstadisticasJ();
        if (estadisticas != null) {
            formulario.setPuntos(String.valueOf(estadisticas.getPuntos()));
            formulario.setRebotes(String.valueOf(estadisticas.getRebotes()));
            formulario.setAsistencias(String.valueOf(estadisticas.getAsistencias()));
            formulario.setRobos(String.valueOf(estadisticas.getRobos()));
            formulario.setTapones(String.valueOf(estadisticas.getTapones()));
            formulario.setID_jugador(String.valueOf(estadisticas.getID_jugador()));
            formulario.setID_partido(String.valueOf(estadisticas.getID_partido()));
        }

        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");

        int result = JOptionPane.showConfirmDialog(null, formulario,
                estadisticas == null ? "Agregar Estadísticas" : "Actualizar Estadísticas", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {

            EstadisticasJugadorPorPartido estadisticasActualizadas = new EstadisticasJugadorPorPartido(
                    estadisticas != null ? estadisticas.getID_estadisticas() : 0,
                    Integer.valueOf(formulario.getPuntos()), Integer.valueOf(formulario.getRebotes()),
                    Integer.valueOf(formulario.getAsistencias()), Integer.valueOf(formulario.getRobos()),
                    Integer.valueOf(formulario.getTapones()), Integer.valueOf(formulario.getID_jugador()),
                    Integer.valueOf(formulario.getID_partido())
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
