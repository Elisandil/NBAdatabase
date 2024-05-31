package modelo;

import java.util.Objects;

/**
 * Clase que representa a un equipo de baloncesto.
 */
public class Equipo {
    private Integer ID_equipo;
    private String nombre;
    private String ciudad;
    private String division;
    private String conferencia;
    private String estadio;
    private String entrenador;
    
    private static final String[] CONFERENCIAS = {"Este", "Oeste"};
    private static final String[] DIVISIONES_ESTE = {"Atlántico", "Central", "Sureste"};
    private static final String[] DIVISIONES_OESTE = {"Noroeste", "Suroeste", "Pacífico"};    

    /**
     * Constructor de la clase Equipo.
     * 
     * @param ID_equipo El ID del equipo.
     * @param nombre El nombre del equipo.
     * @param ciudad La ciudad del equipo.
     * @param division La división del equipo.
     * @param conferencia La conferencia del equipo.
     * @param estadio El estadio del equipo.
     * @param entrenador El entrenador del equipo.
     */    
    public Equipo(Integer ID_equipo,
            String nombre,
            String ciudad,
            String division,
            String conferencia,
            String estadio,
            String entrenador) {
        this.ID_equipo = ID_equipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        setDivision(division);
        setConferencia(conferencia);
        this.estadio = estadio;
        this.entrenador = entrenador;
    }
    
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Integer getID_equipo() {
        return ID_equipo;
    }

    public void setID_equipo(Integer ID_equipo) {
        this.ID_equipo = ID_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.ID_equipo);
        hash = 13 * hash + Objects.hashCode(this.nombre);
        hash = 13 * hash + Objects.hashCode(this.ciudad);
        hash = 13 * hash + Objects.hashCode(this.division);
        hash = 13 * hash + Objects.hashCode(this.conferencia);
        hash = 13 * hash + Objects.hashCode(this.estadio);
        hash = 13 * hash + Objects.hashCode(this.entrenador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.division, other.division)) {
            return false;
        }
        if (!Objects.equals(this.conferencia, other.conferencia)) {
            return false;
        }
        if (!Objects.equals(this.estadio, other.estadio)) {
            return false;
        }
        if (!Objects.equals(this.entrenador, other.entrenador)) {
            return false;
        }
        return Objects.equals(this.ID_equipo, other.ID_equipo);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "ID_equipo=" + ID_equipo + 
                ", nombre=" + nombre + 
                ", ciudad=" + ciudad + 
                ", division=" + division + 
                ", conferencia=" + conferencia + 
                ", estadio=" + estadio + 
                ", entrenador=" + entrenador + 
                '}';
    }
}
