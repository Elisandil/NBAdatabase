package controlador;

import vista.formularios.FormularioEquipo;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.accesoADatos.EquipoDAO;
import vista.Vista;

public class ControladorEquipo implements Actualizar<Equipo> {
    private Vista vista;
    private EquipoDAO equipoDAO;

    public ControladorEquipo(Vista vista, EquipoDAO equipoDAO) {
        this.vista = vista;
        this.equipoDAO = equipoDAO;
    }

    @Override
    public List<Equipo> obtener() {
        return equipoDAO.obtenerTodos();
    }

    public void actualizarTablaEquipo() {
        List<Equipo> equipos = obtener();
        String[] columnas = {"ID", "Nombre", "Ciudad", "Division", "Conferencia", "Estadio", "Entrenador"};
        Object[][] datos = new Object[equipos.size()][columnas.length];
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            datos[i][0] = equipo.getID_equipo();
            datos[i][1] = equipo.getNombre();
            datos[i][2] = equipo.getCiudad();
            datos[i][3] = equipo.getDivision();
            datos[i][4] = equipo.getConferencia();
            datos[i][5] = equipo.getEstadio();
            datos[i][6] = equipo.getEntrenador();
        }
        actualizarTabla(vista, datos, columnas);
    }
    
    private void actualizarTablaEquipo(List<Equipo> equipos) {
        String[] columnas = {"ID", "Nombre", "Ciudad", "Division", "Conferencia", "Estadio", "Entrenador"};
        Object[][] datos = new Object[equipos.size()][columnas.length];
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            datos[i][0] = equipo.getID_equipo();
            datos[i][1] = equipo.getNombre();
            datos[i][2] = equipo.getCiudad();
            datos[i][3] = equipo.getDivision();
            datos[i][4] = equipo.getConferencia();
            datos[i][5] = equipo.getEstadio();
            datos[i][6] = equipo.getEntrenador();
        }
        actualizarTabla(vista, datos, columnas);
    }    

    public void agregarEquipo() {
        Equipo nuevoEquipo = mostrarFormularioEquipo(null);
        if (nuevoEquipo != null) {
            equipoDAO.insertar(nuevoEquipo);
            actualizarTablaEquipo();
            JOptionPane.showMessageDialog(vista, "Equipo insertado correctamente.");            
        }
    }

    public void eliminarEquipo(Integer id) {
        equipoDAO.eliminar(id);
        actualizarTablaEquipo();
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        Equipo equipo = equipoDAO.obtenerPorNombre(nombre);
        if (equipo != null) {
            actualizarTablaEquipo(Arrays.asList(equipo));
        }
        return equipo;
    }

    public List<Equipo> buscarEquipoPorCiudad(String ciudad) {
        List<Equipo> equipos = equipoDAO.obtenerPorCiudad(ciudad);
        actualizarTablaEquipo(equipos);
        return equipos;
    }

    public List<Equipo> buscarEquipoPorDivision(String division) {
        List<Equipo> equipos = equipoDAO.obtenerPorDivision(division);
        actualizarTablaEquipo(equipos);
        return equipos;
    }

    public List<Equipo> buscarEquipoPorConferencia(String conferencia) {
        List<Equipo> equipos = equipoDAO.obtenerPorConferencia(conferencia);
        actualizarTablaEquipo(equipos);
        return equipos;
    }

    public Equipo buscarEquipoPorEstadio(String estadio) {
        Equipo equipo = equipoDAO.obtenerPorEstadio(estadio);
        if (equipo != null) {
            actualizarTablaEquipo(Arrays.asList(equipo));
        }
        return equipo;
    }

    public Equipo buscarEquipoPorEntrenador(String entrenador) {
        Equipo equipo = equipoDAO.obtenerPorEntrenador(entrenador);
        if (equipo != null) {
            actualizarTablaEquipo(Arrays.asList(equipo));
        }
        return equipo;
    }
    
    public void buscarEquipos(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Por Nombre" -> {
                String nombreEquipo = JOptionPane.showInputDialog(vista, "Ingrese el nombre del equipo:", 
                        "Búsqueda por Nombre", JOptionPane.PLAIN_MESSAGE);
                if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
                    buscarEquipoPorNombre(nombreEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un nombre válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Ciudad" -> {
                String ciudadEquipo = JOptionPane.showInputDialog(vista, "Ingrese la ciudad del equipo:", 
                        "Búsqueda por Ciudad", JOptionPane.PLAIN_MESSAGE);
                if (ciudadEquipo != null && !ciudadEquipo.isEmpty()) {
                    buscarEquipoPorCiudad(ciudadEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una ciudad válida.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por División" -> {
                String divisionEquipo = JOptionPane.showInputDialog(vista, "Ingrese la división del equipo:", 
                        "Búsqueda por División", JOptionPane.PLAIN_MESSAGE);
                if (divisionEquipo != null && !divisionEquipo.isEmpty()) {
                    buscarEquipoPorDivision(divisionEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una división válida.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Conferencia" -> {
                String conferenciaEquipo = JOptionPane.showInputDialog(vista, "Ingrese la conferencia del equipo:", 
                        "Búsqueda por Conferencia", JOptionPane.PLAIN_MESSAGE);
                if (conferenciaEquipo != null && !conferenciaEquipo.isEmpty()) {
                    buscarEquipoPorConferencia(conferenciaEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese una conferencia válida.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Estadio" -> {
                String estadioEquipo = JOptionPane.showInputDialog(vista, "Ingrese el estadio del equipo:", 
                        "Búsqueda por Estadio", JOptionPane.PLAIN_MESSAGE);
                if (estadioEquipo != null && !estadioEquipo.isEmpty()) {
                    buscarEquipoPorEstadio(estadioEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un estadio válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Por Entrenador" -> {
                String entrenadorEquipo = JOptionPane.showInputDialog(vista, "Ingrese el entrenador del equipo:", 
                        "Búsqueda por Entrenador", JOptionPane.PLAIN_MESSAGE);
                if (entrenadorEquipo != null && !entrenadorEquipo.isEmpty()) {
                    buscarEquipoPorEntrenador(entrenadorEquipo);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un entrenador válido.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }            
        }
    }    

    public void actualizarEquipo(Integer id) {
        Equipo equipo = equipoDAO.obtenerPorId(id);
        if (equipo != null) {
            Equipo equipoActualizado = mostrarFormularioEquipo(equipo);
            if (equipoActualizado != null) {
                equipoDAO.actualizar(equipoActualizado);
                actualizarTablaEquipo();
            }
        } else {
            mostrarError("El equipo no existe.");
        }
    }

    public void actualizarEquipoDesdeTabla(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            int idEquipo = (int) vista.getTableModel().getValueAt(filaSeleccionada, 0);
            actualizarEquipo(idEquipo);
        } else {
            mostrarError("Por favor, seleccione un equipo para actualizar.");
        }
    }

    private Equipo mostrarFormularioEquipo(Equipo equipo) {
        FormularioEquipo formulario = new FormularioEquipo();
        if (equipo != null) {
            formulario.setNombre(equipo.getNombre());
            formulario.setCiudad(equipo.getCiudad());
            formulario.setDivision(equipo.getDivision());
            formulario.setConferencia(equipo.getConferencia());
            formulario.setEstadio(equipo.getEstadio());
            formulario.setEntrenador(equipo.getEntrenador());
        }
        ImageIcon icono = new ImageIcon("src/main/java/util/icono2redimensionado.png");
        int result = JOptionPane.showConfirmDialog(null, formulario,
                equipo == null ? "Agregar Equipo" : "Actualizar Equipo", JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE, icono);
        if (result == JOptionPane.OK_OPTION) {
            return new Equipo(
                    equipo != null ? equipo.getID_equipo() : 0,
                    formulario.getNombre(), formulario.getCiudad(),
                    formulario.getDivision(), formulario.getConferencia(),
                    formulario.getEstadio(), formulario.getEntrenador()
            );
        }
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
