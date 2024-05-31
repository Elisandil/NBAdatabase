package modelo;

import java.util.Objects;

public class EstadisticasJugadorPorPartido {
    private Integer ID_estadisticas;
    private Integer puntos;
    private Integer rebotes;
    private Integer asistencias;
    private Integer robos;
    private Integer tapones;
    private Integer ID_jugador;
    private Integer ID_partido;

    public EstadisticasJugadorPorPartido(Integer ID_estadisticas, 
                                         Integer puntos, 
                                         Integer rebotes, 
                                         Integer asistencias, 
                                         Integer robos, 
                                         Integer tapones, 
                                         Integer ID_jugador, 
                                         Integer ID_partido) {
        this.ID_estadisticas = ID_estadisticas;
        setPuntos(puntos);
        setRebotes(rebotes);
        setAsistencias(asistencias);
        setRobos(robos);
        setTapones(tapones);
        setID_jugador(ID_jugador);
        setID_partido(ID_partido);
    }

    public Integer getID_estadisticas() {
        return ID_estadisticas;
    }

    public void setID_estadisticas(Integer ID_estadisticas) {
        this.ID_estadisticas = ID_estadisticas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        if (puntos < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos.");
        }
        this.puntos = puntos;
    }

    public Integer getRebotes() {
        return rebotes;
    }

    public void setRebotes(Integer rebotes) {
        if (rebotes < 0) {
            throw new IllegalArgumentException("Los rebotes no pueden ser negativos.");
        }
        this.rebotes = rebotes;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        if (asistencias < 0) {
            throw new IllegalArgumentException("Las asistencias no pueden ser negativas.");
        }
        this.asistencias = asistencias;
    }

    public Integer getRobos() {
        return robos;
    }

    public void setRobos(Integer robos) {
        if (robos < 0) {
            throw new IllegalArgumentException("Los robos no pueden ser negativos.");
        }
        this.robos = robos;
    }

    public Integer getTapones() {
        return tapones;
    }

    public void setTapones(Integer tapones) {
        if (tapones < 0) {
            throw new IllegalArgumentException("Los tapones no pueden ser negativos.");
        }
        this.tapones = tapones;
    }

    public Integer getID_jugador() {
        return ID_jugador;
    }

    public void setID_jugador(Integer ID_jugador) {
        if (ID_jugador < 0) {
            throw new IllegalArgumentException("El ID del jugador debe ser positivo.");
        }
        this.ID_jugador = ID_jugador;
    }

    public Integer getID_partido() {
        return ID_partido;
    }

    public void setID_partido(Integer ID_partido) {
        if (ID_partido < 0) {
            throw new IllegalArgumentException("El ID del partido debe ser positivo.");
        }
        this.ID_partido = ID_partido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ID_estadisticas);
        hash = 71 * hash + Objects.hashCode(this.puntos);
        hash = 71 * hash + Objects.hashCode(this.rebotes);
        hash = 71 * hash + Objects.hashCode(this.asistencias);
        hash = 71 * hash + Objects.hashCode(this.robos);
        hash = 71 * hash + Objects.hashCode(this.tapones);
        hash = 71 * hash + Objects.hashCode(this.ID_jugador);
        hash = 71 * hash + Objects.hashCode(this.ID_partido);
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
        final EstadisticasJugadorPorPartido other = (EstadisticasJugadorPorPartido) obj;
        if (!Objects.equals(this.ID_estadisticas, other.ID_estadisticas)) {
            return false;
        }
        if (!Objects.equals(this.puntos, other.puntos)) {
            return false;
        }
        if (!Objects.equals(this.rebotes, other.rebotes)) {
            return false;
        }
        if (!Objects.equals(this.asistencias, other.asistencias)) {
            return false;
        }
        if (!Objects.equals(this.robos, other.robos)) {
            return false;
        }
        if (!Objects.equals(this.tapones, other.tapones)) {
            return false;
        }
        if (!Objects.equals(this.ID_jugador, other.ID_jugador)) {
            return false;
        }
        return Objects.equals(this.ID_partido, other.ID_partido);
    }

    @Override
    public String toString() {
        return "EstadisticasJugadorPorPartido{" +
                "ID_estadisticas=" + ID_estadisticas +
                ", puntos=" + puntos +
                ", rebotes=" + rebotes +
                ", asistencias=" + asistencias +
                ", robos=" + robos +
                ", tapones=" + tapones +
                ", ID_jugador=" + ID_jugador +
                ", ID_partido=" + ID_partido +
                '}';
    }
}
