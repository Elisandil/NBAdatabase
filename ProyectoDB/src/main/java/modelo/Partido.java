package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Partido {
    private Integer ID_partido;
    private LocalDate fechaPartido;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Integer puntuacionLocal;
    private Integer puntuacionVisitante;
    private Integer ID_temporada;

    public Partido(Integer ID_partido, LocalDate fechaPartido, Equipo equipoLocal, 
                   Equipo equipoVisitante, Integer puntuacionLocal, Integer puntuacionVisitante, 
                   Integer ID_temporada) {
        this.ID_partido = ID_partido;
        this.fechaPartido = fechaPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        setPuntuacionLocal(puntuacionLocal);
        setPuntuacionVisitante(puntuacionVisitante);
        this.ID_temporada = ID_temporada;
    }

    public Integer getID_partido() {
        return ID_partido;
    }

    public void setID_partido(Integer ID_partido) {
        this.ID_partido = ID_partido;
    }

    public LocalDate getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(LocalDate fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        if (equipoLocal == null) {
            throw new IllegalArgumentException("El equipo local debe de existir.");
        }
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        if (equipoVisitante == null) {
            throw new IllegalArgumentException("El equipo visitante debe de existir.");
        }
        this.equipoVisitante = equipoVisitante;
    }

    public Integer getPuntuacionLocal() {
        return puntuacionLocal;
    }

    public void setPuntuacionLocal(Integer puntuacionLocal) {
        if (puntuacionLocal < 0) {
            throw new IllegalArgumentException("La puntuación local no puede ser negativa.");
        }
        this.puntuacionLocal = puntuacionLocal;
    }

    public Integer getPuntuacionVisitante() {
        return puntuacionVisitante;
    }

    public void setPuntuacionVisitante(Integer puntuacionVisitante) {
        if (puntuacionVisitante < 0) {
            throw new IllegalArgumentException("La puntuación visitante no puede ser negativa.");
        }
        this.puntuacionVisitante = puntuacionVisitante;
    }

    public Integer getID_temporada() {
        return ID_temporada;
    }

    public void setID_temporada(Integer ID_temporada) {
        if (ID_temporada < 0) {
            throw new IllegalArgumentException("El ID de la temporada tiene que ser positivo y además existir.");
        }
        this.ID_temporada = ID_temporada;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.ID_partido);
        hash = 41 * hash + Objects.hashCode(this.fechaPartido);
        hash = 41 * hash + Objects.hashCode(this.equipoLocal);
        hash = 41 * hash + Objects.hashCode(this.equipoVisitante);
        hash = 41 * hash + Objects.hashCode(this.puntuacionLocal);
        hash = 41 * hash + Objects.hashCode(this.puntuacionVisitante);
        hash = 41 * hash + Objects.hashCode(this.ID_temporada);
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
        final Partido other = (Partido) obj;
        if (!Objects.equals(this.ID_partido, other.ID_partido)) {
            return false;
        }
        if (!Objects.equals(this.fechaPartido, other.fechaPartido)) {
            return false;
        }
        if (!Objects.equals(this.equipoLocal, other.equipoLocal)) {
            return false;
        }
        if (!Objects.equals(this.equipoVisitante, other.equipoVisitante)) {
            return false;
        }
        if (!Objects.equals(this.puntuacionLocal, other.puntuacionLocal)) {
            return false;
        }
        if (!Objects.equals(this.puntuacionVisitante, other.puntuacionVisitante)) {
            return false;
        }
        return Objects.equals(this.ID_temporada, other.ID_temporada);
    }

    @Override
    public String toString() {
        return "Partido{" + 
                "ID_partido=" + ID_partido + 
                ", fechaPartido=" + fechaPartido + 
                ", equipoLocal=" + equipoLocal + 
                ", equipoVisitante=" + equipoVisitante + 
                ", puntuacionLocal=" + puntuacionLocal + 
                ", puntuacionVisitante=" + puntuacionVisitante + 
                ", ID_temporada=" + ID_temporada + '}';
    }  
}