package controlador;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.accesoADatos.EquipoDAO;
import modelo.accesoADatos.EstadisticasEquipoDAO;
import modelo.accesoADatos.EstadisticasJugadorDAO;
import modelo.accesoADatos.JugadorDAO;
import modelo.accesoADatos.PartidoDAO;
import modelo.accesoADatos.PremioDAO;
import modelo.accesoADatos.TemporadaDAO;
import vista.Vista;
import vista.VistaPrincipal;
import vista.VistaTablaUnica;

public class ControladorPrincipal {
    private VistaPrincipal vistaPrincipal;
    private Vista vista;
    private VistaTablaUnica vista2;
    private ControladorEquipo controladorEquipo;
    private ControladorJugador controladorJugador;
    private ControladorPartido controladorPartido;
    private ControladorTemporada controladorTemporada;
    private ControladorEstadisticasJ controladorEstadisticasJ;
    private ControladorEstadisticasE controladorEstadisticasE;
    private ControladorPremio controladorPremio;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal, EquipoDAO equipoDAO, PartidoDAO partidoDAO,
                                JugadorDAO jugadorDAO, TemporadaDAO temporadaDAO, EstadisticasJugadorDAO estadisticasJugadorDAO,
                                EstadisticasEquipoDAO estadisticasEquipoDAO, PremioDAO premioDAO) {
        this.vistaPrincipal = vistaPrincipal;
        this.vista = new Vista();
        this.vista2 = new VistaTablaUnica();
        this.controladorEquipo = new ControladorEquipo(vista, equipoDAO);
        this.controladorJugador = new ControladorJugador(vista, jugadorDAO);
        this.controladorPartido = new ControladorPartido(vista, partidoDAO, controladorEquipo);
        this.controladorTemporada = new ControladorTemporada(vista, temporadaDAO);
        this.controladorEstadisticasJ = new ControladorEstadisticasJ(vista, estadisticasJugadorDAO);
        this.controladorEstadisticasE = new ControladorEstadisticasE(vista, vista2, estadisticasEquipoDAO);
        this.controladorPremio = new ControladorPremio(vista, premioDAO);
        initController();
        vistaPrincipal.setResizable(false);
        vistaPrincipal.setVisible(true);
    }

    private void initController() {
        vistaPrincipal.getCargarDatosButton().addActionListener(e -> cargarDatos());
        vista.getBtnVolver().addActionListener(e -> volverHome());
        vista.getComboBox().addActionListener(e -> actualizarTabla((String) ((JComboBox<String>) e.getSource()).getSelectedItem()));
        vistaPrincipal.getSalirButton().addActionListener(e -> System.exit(0));
        vista.getBtnAdd().addActionListener(e -> agregarItem((String) vista.getComboBox().getSelectedItem()));
        vista.getBtnBorrarPorId().addActionListener(e -> borrarPorId((String) vista.getComboBox().getSelectedItem()));
        vista.getBtnBuscar().addActionListener(e -> mostrarOpcionesBusqueda((String) vista.getComboBox().getSelectedItem()));
        vista.getBtnActualizar().addActionListener(e -> actualizarItem());
    }

    private void cargarDatos() {
        vista.setVisible(true);
        vistaPrincipal.dispose();
    }

    private void volverHome() {
        vistaPrincipal.setVisible(true);
        vista.dispose();
    }

    private void actualizarTabla(String selectedTable) {
        switch (selectedTable) {
            case "----" -> {
                while (vista.getTable().getRowCount() > 0) {
                    vista.getTableModel().setColumnCount(0);
                    vista.getTableModel().setRowCount(0);
                }
                JOptionPane.showMessageDialog(vista, "No hay nada seleccionado.");
            }
            case "Equipos" -> {
                controladorEquipo.obtener();
                controladorEquipo.actualizarTablaEquipo();
            }
            case "Jugadores" -> {
                controladorJugador.obtener();
                controladorJugador.actualizarTablaJugador();
            }
            case "Partidos" -> {
                controladorPartido.obtener();
                controladorPartido.actualizarTablaPartido();
            }
            case "Temporadas" -> {
                controladorTemporada.obtener();
                controladorTemporada.actualizarTablaTemporada();
            }
            case "Estadisticas" -> {
                controladorEstadisticasJ.obtener();
                controladorEstadisticasJ.actualizarTablaEstadisticasJ();
            }
            case "Estadisticas Equipo" -> {
                controladorEstadisticasE.obtener();
                controladorEstadisticasE.actualizarTablaEstadisticasE();
            }
            case "Premios" -> {
                controladorPremio.obtener();
                controladorPremio.actualizarTablaPremios();
            }            
        }
    }

    private void agregarItem(String selectedTable) {
        if (selectedTable != null) {
            switch (selectedTable) {
                case "Equipos" -> controladorEquipo.agregarEquipo();
                case "Jugadores" -> controladorJugador.agregarJugador();
                case "Partidos" -> controladorPartido.agregarPartido();
                case "Temporadas" -> controladorTemporada.agregarTemporada();
                case "Estadisticas" -> controladorEstadisticasJ.agregarEstadisticasJ();
                case "Estadisticas Equipo" -> controladorEstadisticasE.agregarEstadisticasE();
                case "Premios" -> controladorPremio.agregarPremio();
            }
        }
    }

    private void borrarPorId(String selectedTable) {
        String idText = JOptionPane.showInputDialog(vista, "Ingrese el ID:", 
                "Borrar por ID", JOptionPane.PLAIN_MESSAGE);
        if (idText != null && !idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                switch (selectedTable) {
                    case "Equipos" -> controladorEquipo.eliminarEquipo(id);
                    case "Jugadores" -> controladorJugador.eliminarJugador(id);
                    case "Partidos" -> controladorPartido.eliminarPartido(id);
                    case "Temporadas" -> controladorTemporada.eliminarTemporada(id);
                    case "Estadisticas" -> controladorEstadisticasJ.eliminarEstadisticasJ(id);
                    case "Estadisticas Equipo" -> controladorEstadisticasE.eliminarEstadisticasE(id);
                    case "Premios" -> controladorPremio.eliminarPremio(id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "ID inválido. Por favor, ingrese un número entero.", "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese un ID válido.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarOpcionesBusqueda(String selectedTable) {
        String[] opciones = null;
        String mensaje = null;

        switch (selectedTable) {
            case "Equipos" -> {
                opciones = new String[]{"Por Nombre", "Por Ciudad", "Por División", "Por Conferencia", "Por Estadio", "Por Entrenador"};
                mensaje = "Selecciona una opción para buscar equipos:";
            }
            case "Jugadores" -> {
                opciones = new String[]{"Por Apellido", "Por Posición", "Por Nombre del Equipo"};
                mensaje = "Selecciona una opción para buscar jugadores:";
            }
            case "Partidos" -> {
                opciones = new String[]{"Por Equipo Local", "Por Equipo Visitante", "Por Fecha"};
                mensaje = "Selecciona una opción para buscar partidos:";
            }
            case "Temporadas" -> {
                opciones = new String[]{"Por Año", "Por Tipo"};
                mensaje = "Selecciona una opción para buscar temporadas:";
            }
            case "Estadisticas" -> {
                opciones = new String[]{"Por ID Jugador", "Por ID Partido"};
                mensaje = "Selecciona una opción para buscar estadísticas de jugadores:";
            }
            case "Estadisticas Equipo" -> {
                opciones = new String[]{"Por Victorias", "Por Porcentaje de Victorias"};
                mensaje = "Selecciona una opción para buscar estadísticas de equipo:";
            }
            case "Premios" -> {
                opciones = new String[]{"Por Tipo", "Por Status"};
                mensaje = "Selecciona una opción para buscar un premio";
            }
            default -> {
                JOptionPane.showMessageDialog(vista, "Por favor, selecciona una tabla válida.", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                vista, mensaje, "Buscar", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (opcionSeleccionada != null) {
            switch (selectedTable) {
                case "Equipos" -> controladorEquipo.buscarEquipos(opcionSeleccionada);
                case "Jugadores" -> controladorJugador.buscarJugadores(opcionSeleccionada);
                case "Partidos" -> controladorPartido.buscarPartidos(opcionSeleccionada);
                case "Temporadas" -> controladorTemporada.buscarTemporadas(opcionSeleccionada);
                case "Estadisticas" -> controladorEstadisticasJ.buscarEstadisticasJugador(opcionSeleccionada);
                case "Estadisticas Equipo" -> controladorEstadisticasE.buscarEstadisticasEquipo(opcionSeleccionada);
                case "Premios" -> controladorPremio.buscarPremios(opcionSeleccionada);
            }
        }
    }

    private void actualizarItem() {
        String selectedTable = (String) vista.getComboBox().getSelectedItem();
        int filaSeleccionada = vista.getTable().getSelectedRow();
        if (selectedTable != null) {
            switch (selectedTable) {
                case "Equipos" -> controladorEquipo.actualizarEquipoDesdeTabla(filaSeleccionada);
                case "Jugadores" -> controladorJugador.actualizarJugadorDesdeTabla(filaSeleccionada);
                case "Partidos" -> controladorPartido.actualizarPartidoDesdeTabla(filaSeleccionada);
                case "Temporadas" -> controladorTemporada.actualizarTemporadaDesdeTabla(filaSeleccionada);
                case "Estadisticas" -> controladorEstadisticasJ.actualizarEstadisticasDesdeTabla(filaSeleccionada);
                case "Estadisticas Equipo" -> controladorEstadisticasE.actualizarEstadisticasDesdeTabla(filaSeleccionada);
                case "Premios" -> controladorPremio.actualizarPremioDesdeTabla(filaSeleccionada);
            }
        }
    }
}  

