package controlador;

import vista.formularios.FormularioJugador;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Jugador;
import modelo.accesoADatos.JugadorDAO;
import vista.Vista;

public class ControladorJugador implements Actualizar<Jugador> {
    private Vista vista;
    private JugadorDAO jugadorDAO;

    public ControladorJugador(Vista vista, JugadorDAO jugadorDAO) {
        this.vista = vista;
        this.jugadorDAO = jugadorDAO;
    }

    @Override
    public List<Jugador> obtener() {
        return jugadorDAO.obtenerTodos();
    }

    public void actualizarTablaJugador() {
        List<Jugador> jugadores = obtener();
        String[] columnas = {"ID", "Nombre", "Apellido", "Posición", "Altura", "Peso", "Fecha Nacimiento", "ID Equipo"};
        Object[][] datos = jugadores.stream().map(j -> new Object[]{
            j.getID_jugador(), j.getNombre(), j.getApellido(), j.getPosicion(),
            j.getAltura(), j.getPeso(), j.getFechaNacimiento(), j.getId_equipo()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }
    
    private void actualizarTablaJugador(List<Jugador> jugadores) {
        String[] columnas = {"ID", "Nombre", "Apellido", "Posición", "Altura", "Peso", "Fecha Nacimiento", "ID Equipo"};
        Object[][] datos = jugadores.stream().map(j -> new Object[]{
            j.getID_jugador(), j.getNombre(), j.getApellido(), j.getPosicion(),
            j.getAltura(), j.getPeso(), j.getFechaNacimiento(), j.getId_equipo()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }    

    public void agregarJugador() {
        Jugador nuevoJugador = mostrarFormularioJugador(null);
        if (nuevoJugador != null) {
            jugadorDAO.insertar(nuevoJugador);
            actualizarTablaJugador();
            JOptionPane.showMessageDialog(vista, "Jugador insertado correctamente.");            
        }
    }

    public void eliminarJugador(int id) {
        jugadorDAO.eliminar(id);
        actualizarTablaJugador();
    }

    public List<Jugador> buscarJugadorPorApellido(String apellido) {
        List<Jugador> jugadores = jugadorDAO.obtenerPorApellidos(apellido);
        actualizarTablaJugador(jugadores);
        return jugadores;
    }

    public List<Jugador> buscarJugadorPorPosicion(String posicion) {
        List<Jugador> jugadores = jugadorDAO.obtenerPorPosicion(posicion);
        actualizarTablaJugador(jugadores);
        return jugadores;
    }

    public List<Jugador> buscarJugadorPorNombreEquipo(String nombreEquipo) {
        List<Jugador> jugadores = jugadorDAO.obtenerPorNombreEquipo(nombreEquipo);
        actualizarTablaJugador(jugadores);
        return jugadores;
    }
    
    public void buscarJugadores(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Apellido" -> {
                String apellidoJugador = JOptionPane.showInputDialog(vista, "Ingrese el apellido del jugador:", 
                        "Búsqueda por Apellido", JOptionPane.PLAIN_MESSAGE);
                if (apellidoJugador != null && !apellidoJugador.isEmpty()) {
                    buscarJugadorPorApellido(apellidoJugador);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un apellido válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Posición" -> {
                String posicionJugador = JOptionPane.showInputDialog(vista, "Ingrese la posición del jugador:", 
                        "Búsqueda por Posición", JOptionPane.PLAIN_MESSAGE);
                if (posicionJugador != null && !posicionJugador.isEmpty()) {
                    buscarJugadorPorPosicion(posicionJugador);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una posición válida.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Nombre del Equipo" -> {
                String nombreEquipo = JOptionPane.showInputDialog(vista, "Ingrese el nombre del equipo:", 
                        "Búsqueda por Nombre del Equipo", JOptionPane.PLAIN_MESSAGE);
                if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
                    buscarJugadorPorNombreEquipo(nombreEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un nombre de equipo válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }   

    public void actualizarJugador(Integer id) {
        Jugador jugador = jugadorDAO.obtenerPorId(id);
        if (jugador != null) {
            Jugador jugadorActualizado = mostrarFormularioJugador(jugador);
            if (jugadorActualizado != null) {
                jugadorDAO.actualizar(jugadorActualizado);
                actualizarTablaJugador();
            }
        } else {
            mostrarError("El jugador no existe.");
        }
    }

    public void actualizarJugadorDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idJugador = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarJugador(idJugador);
        } else {
            mostrarError("Por favor, seleccione un jugador para actualizar.");
        }
    }

    private Jugador mostrarFormularioJugador(Jugador jugador) {
        FormularioJugador formulario = new FormularioJugador();
        if (jugador != null) {
            formulario.setNombre(jugador.getNombre());
            formulario.setApellido(jugador.getApellido());
            formulario.setPosicion(jugador.getPosicion());
            formulario.setAltura(String.valueOf(jugador.getAltura()));
            formulario.setPeso(String.valueOf(jugador.getPeso()));
            formulario.setFechaNacimiento(jugador.getFechaNacimiento().toString());
            formulario.setID_equipo(String.valueOf(jugador.getId_equipo()));
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");
        int result = JOptionPane.showConfirmDialog(null, formulario, 
            jugador == null ? "Agregar Jugador" : "Actualizar Jugador", JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {
            return new Jugador(
                jugador != null ? jugador.getID_jugador() : 0,
                formulario.getNombre(), formulario.getApellido(),
                formulario.getPosicion(), formulario.getAltura(),
                formulario.getPeso(), LocalDate.parse(formulario.getFechaNacimiento()),
                formulario.getID_equipo()
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
