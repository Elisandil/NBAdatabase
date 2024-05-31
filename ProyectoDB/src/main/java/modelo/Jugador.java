package modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa a un jugador de baloncesto.
 */
public class Jugador {
    private Integer ID_jugador;
    private String nombre;
    private String apellido;
    private String posicion;
    private Double altura;
    private Double peso;
    private LocalDate fechaNacimiento;
    private Integer id_equipo;

    /**
     * Constructor de la clase Jugador.
     * 
     * @param ID_jugador El ID del jugador.
     * @param nombre El nombre del jugador.
     * @param apellido El apellido del jugador.
     * @param posicion La posicion del jugador.
     * @param altura La altura del jugador en metros.
     * @param peso El peso del jugador en kilogramos.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param id_equipo El ID del equipo al que pertenece. 
     */    
    public Jugador(Integer ID_jugador, 
            String nombre,
            String apellido,
            String posicion, 
            Double altura, 
            Double peso, 
            LocalDate fechaNacimiento,
            Integer id_equipo) {
        this.ID_jugador = ID_jugador;
        setNombre(nombre);
        setApellido(apellido);
        setPosicion(posicion);
        setAltura(altura);
        setPeso(peso);
        setFechaNacimiento(fechaNacimiento);
        this.id_equipo = id_equipo;
    }

    public Integer getID_jugador() {
        return ID_jugador;
    }

    public void setID_jugador(Integer ID_jugador) {
        this.ID_jugador = ID_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío.");
        }        
        this.apellido = apellido;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        if (altura < 1.5d || altura > 2.5d) {
            throw new IllegalArgumentException("La altura no es real.");
        }
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        if (peso < 0d || peso > 200d) {
            throw new IllegalArgumentException("El peso no es real.");
        }
        this.peso = peso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(Integer id_equipo) {
        this.id_equipo = id_equipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.ID_jugador);
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.apellido);
        hash = 89 * hash + Objects.hashCode(this.posicion);
        hash = 89 * hash + Objects.hashCode(this.altura);
        hash = 89 * hash + Objects.hashCode(this.peso);
        hash = 89 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 89 * hash + Objects.hashCode(this.id_equipo);
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
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.posicion, other.posicion)) {
            return false;
        }
        if (!Objects.equals(this.ID_jugador, other.ID_jugador)) {
            return false;
        }
        if (!Objects.equals(this.altura, other.altura)) {
            return false;
        }
        if (!Objects.equals(this.peso, other.peso)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return Objects.equals(this.id_equipo, other.id_equipo);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "ID_jugador=" + ID_jugador + 
                ", nombre=" + nombre + 
                ", apellido=" + apellido + 
                ", posicion=" + posicion +
                ", altura=" + altura + 
                ", peso=" + peso + 
                ", fechaNacimiento=" + fechaNacimiento + 
                ", id_equipo=" + id_equipo + 
                '}';
    }
}
