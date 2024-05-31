package controlador;

import vista.formularios.FormularioPartido;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.Partido;
import modelo.accesoADatos.PartidoDAO;
import vista.Vista;

public class ControladorPartido implements Actualizar<Partido> {
    private Vista vista;
    private PartidoDAO partidoDAO;
    private ControladorEquipo controladorEquipo;

    public ControladorPartido(Vista vista, PartidoDAO partidoDAO, ControladorEquipo controladorEquipo) {
        this.vista = vista;
        this.partidoDAO = partidoDAO;
        this.controladorEquipo = controladorEquipo;
    }

    @Override
    public List<Partido> obtener() {
        return partidoDAO.obtenerTodos();
    }

    public void actualizarTablaPartido() {
        List<Partido> partidos = obtener();
        String[] columnas = {"ID", "Fecha", "Equipo Local", "Equipo Visitante", 
                             "Puntuación Local", "Puntuación Visitante", "ID Temporada"};
        Object[][] datos = partidos.stream().map(p -> new Object[]{
            p.getID_partido(), p.getFechaPartido(), p.getEquipoLocal().getNombre(),
            p.getEquipoVisitante().getNombre(), p.getPuntuacionLocal(),
            p.getPuntuacionVisitante(), p.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }
    
    public void actualizarTablaPartido(List<Partido> partidos) {
        String[] columnas = {"ID", "Fecha", "Equipo Local", "Equipo Visitante", 
                             "Puntuación Local", "Puntuación Visitante", "ID Temporada"};
        Object[][] datos = partidos.stream().map(p -> new Object[]{
            p.getID_partido(), p.getFechaPartido(), p.getEquipoLocal().getNombre(),
            p.getEquipoVisitante().getNombre(), p.getPuntuacionLocal(),
            p.getPuntuacionVisitante(), p.getID_temporada()
        }).toArray(Object[][]::new);
        actualizarTabla(vista, datos, columnas);
    }    

    public void agregarPartido() {
        Partido nuevoPartido = mostrarFormularioPartido(null, controladorEquipo);
        if (nuevoPartido != null) {
            partidoDAO.insertar(nuevoPartido);
            actualizarTablaPartido();
            JOptionPane.showMessageDialog(vista, "Partido insertado correctamente.");            
        }
    }

    public void eliminarPartido(int id) {
        partidoDAO.eliminar(id);
        actualizarTablaPartido();
    }

    public List<Partido> buscarPartidoPorEquipoLocal(String equipoLocal) {
        List<Partido> partidos = partidoDAO.obtenerPorEquipoLocal(equipoLocal);
        actualizarTablaPartido(partidos);
        return partidos;
    }

    public List<Partido> buscarPartidoPorEquipoVisitante(String equipoVisitante) {
        List<Partido> partidos = partidoDAO.obtenerPorEquipoVisitante(equipoVisitante);
        actualizarTablaPartido(partidos);
        return partidos;
    }
    
    public List<Partido> buscarPartidoPorFecha(LocalDate fecha) {
        List<Partido> partidos = partidoDAO.obtenerPorFecha(fecha);
        actualizarTablaPartido(partidos);
        return partidos;
    }
    
    public void buscarPartidos(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Equipo Local" -> {
                String equipoLocal = JOptionPane.showInputDialog(vista, "Ingrese el nombre del equipo local:", 
                        "Búsqueda por Equipo Local", JOptionPane.PLAIN_MESSAGE);
                if (equipoLocal != null && !equipoLocal.isEmpty()) {
                    buscarPartidoPorEquipoLocal(equipoLocal);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un nombre válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Equipo Visitante" -> {
                String equipoVisitante = JOptionPane.showInputDialog(vista, "Ingrese el nombre del equipo visitante:", 
                        "Búsqueda por Equipo Visitante", JOptionPane.PLAIN_MESSAGE);
                if (equipoVisitante != null && !equipoVisitante.isEmpty()) {
                    buscarPartidoPorEquipoVisitante(equipoVisitante);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un nombre válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Fecha" -> {
                String fecha = JOptionPane.showInputDialog(vista, "Ingrese la fecha del partido (YYYY-MM-DD):", 
                        "Búsqueda por Fecha", JOptionPane.PLAIN_MESSAGE);
                if (fecha != null && !fecha.isEmpty()) {
                    try {
                        buscarPartidoPorFecha(LocalDate.parse(fecha));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(vista, "Por favor, ingrese una fecha válida en el formato especificado (YYYY-MM-DD).", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una fecha válida en el formato especificado (YYYY-MM-DD).", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }            
        }
    }    

    public void actualizarPartido(Integer id) {
        Partido partido = partidoDAO.obtenerPorId(id);
        if (partido != null) {
            Partido partidoActualizado = mostrarFormularioPartido(partido, controladorEquipo);
            if (partidoActualizado != null) {
                partidoDAO.actualizar(partidoActualizado);
                actualizarTablaPartido();
            }
        } else {
            mostrarError("El partido no existe.");
        }
    }

    public void actualizarPartidoDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idPartido = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarPartido(idPartido);
        } else {
            mostrarError("Por favor, seleccione un partido para actualizar.");
        }
    }

    private Partido mostrarFormularioPartido(Partido partido, ControladorEquipo controladorEquipo) {
        FormularioPartido formulario = new FormularioPartido();

        if (partido != null) {
            formulario.setFechaPartido(partido.getFechaPartido().toString());
            formulario.setEquipoLocal(partido.getEquipoLocal().getNombre());
            formulario.setEquipoVisitante(partido.getEquipoVisitante().getNombre());
            formulario.setPuntuacionLocal(String.valueOf(partido.getPuntuacionLocal()));
            formulario.setPuntuacionVisitante(String.valueOf(partido.getPuntuacionVisitante()));
            formulario.setID_temporada(String.valueOf(partido.getID_temporada()));
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");
        int result = JOptionPane.showConfirmDialog(null, formulario, 
            partido == null ? "Agregar Partido" : "Actualizar Partido", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE, icono);

        if (result == JOptionPane.OK_OPTION) {
            String nombreEquipoLocal = formulario.getEquipoLocal();
            String nombreEquipoVisitante = formulario.getEquipoVisitante();

            Equipo equipoLocal = controladorEquipo.buscarEquipoPorNombre(nombreEquipoLocal);
            Equipo equipoVisitante = controladorEquipo.buscarEquipoPorNombre(nombreEquipoVisitante);

            if (equipoLocal == null || equipoVisitante == null) {
                JOptionPane.showMessageDialog(null, "Uno o ambos equipos no existen en la base de datos.");
                return null;
            }

            return new Partido(
                partido != null ? partido.getID_partido() : 0,
                LocalDate.parse(formulario.getFechaPartido()), 
                equipoLocal, 
                equipoVisitante, 
                Integer.valueOf(formulario.getPuntuacionLocal()), 
                Integer.valueOf(formulario.getPuntuacionVisitante()), 
                Integer.valueOf(formulario.getID_temporada())
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
