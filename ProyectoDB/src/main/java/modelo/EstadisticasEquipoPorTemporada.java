package modelo;

import java.util.Objects;

public class EstadisticasEquipoPorTemporada {
    private Integer ID_estadisticas;
    private Integer puntosAnotados;
    private Integer puntosEncajados;
    private Integer victorias;
    private Integer derrotas;
    private String registro;
    private Integer ID_equipo;
    private Integer ID_temporada;

    public EstadisticasEquipoPorTemporada(Integer ID_estadisticas, 
                                          Integer puntosAnotados, 
                                          Integer puntosEncajados, 
                                          Integer victorias,
                                          Integer derrotas,
                                          String registro, 
                                          Integer ID_equipo, 
                                          Integer ID_temporada) {
        this.ID_estadisticas = ID_estadisticas;
        setPuntosAnotados(puntosAnotados);
        setPuntosEncajados(puntosEncajados);
        setVictorias(victorias);
        setDerrotas(derrotas);
        setRegistro(registro);
        this.ID_equipo = ID_equipo;
        this.ID_temporada = ID_temporada;
    }

    public Integer getID_estadisticas() {
        return ID_estadisticas;
    }

    public void setID_estadisticas(Integer ID_estadisticas) {
        this.ID_estadisticas = ID_estadisticas;
    }

    public Integer getPuntosAnotados() {
        return puntosAnotados;
    }

    public void setPuntosAnotados(Integer puntosAnotados) {
        if (puntosAnotados < 0 || puntosAnotados > 15000) {
            throw new IllegalArgumentException("Puntos anotados erróneos. No puede haber anotado " + puntosAnotados + " puntos.");
        }
        this.puntosAnotados = puntosAnotados;
    }

    public Integer getPuntosEncajados() {
        return puntosEncajados;
    }

    public void setPuntosEncajados(Integer puntosEncajados) {
        if (puntosEncajados < 0 || puntosEncajados > 15000) {
            throw new IllegalArgumentException("Puntos encajados erróneos. No puede haber encajado " + puntosEncajados + " puntos.");
        }
        this.puntosEncajados = puntosEncajados;
    }

    public Integer getVictorias() {
        return victorias;
    }

    public void setVictorias(Integer victorias) {
        if (victorias < 0 || victorias > 82) {
            throw new IllegalArgumentException("Número de victorias erróneo. No puede haber " + victorias + " victorias.");
        }
        this.victorias = victorias;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        if (derrotas < 0 || derrotas > 82) {
            throw new IllegalArgumentException("Número de derrotas erróneo. No puede haber " + derrotas + " derrotas.");
        }
        this.derrotas = derrotas;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        String[] campos = registro.split("-");
        if (campos.length != 2) {
            throw new IllegalArgumentException("Registro erróneo. El formato debe ser 'victorias-derrotas'.");
        }

        int victorias = Integer.parseInt(campos[0]);
        int derrotas = Integer.parseInt(campos[1]);

        if (victorias < 0 || derrotas < 0 || victorias + derrotas > 82) {
            throw new IllegalArgumentException("Registro erróneo. La suma de victorias y derrotas no puede superar los 82 partidos.");
        }

        if (this.victorias != null && this.derrotas != null) {
            if (this.victorias != victorias || this.derrotas != derrotas) {
                throw new IllegalArgumentException("El registro no coincide con el número de victorias y derrotas.");
            }
        }
        this.registro = registro;
    }

    public Integer getID_equipo() {
        return ID_equipo;
    }

    public void setID_equipo(Integer ID_equipo) {
        if (ID_equipo < 0) {
            throw new IllegalArgumentException("El ID del equipo debe ser positivo.");
        }
        this.ID_equipo = ID_equipo;
    }

    public Integer getID_temporada() {
        return ID_temporada;
    }

    public void setID_temporada(Integer ID_temporada) {
        if (ID_temporada < 0) {
            throw new IllegalArgumentException("El ID de la temporada debe ser positivo.");
        }
        this.ID_temporada = ID_temporada;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.ID_estadisticas);
        hash = 41 * hash + Objects.hashCode(this.puntosAnotados);
        hash = 41 * hash + Objects.hashCode(this.puntosEncajados);
        hash = 41 * hash + Objects.hashCode(this.victorias);
        hash = 41 * hash + Objects.hashCode(this.derrotas);
        hash = 41 * hash + Objects.hashCode(this.registro);
        hash = 41 * hash + Objects.hashCode(this.ID_equipo);
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
        final EstadisticasEquipoPorTemporada other = (EstadisticasEquipoPorTemporada) obj;
        if (!Objects.equals(this.registro, other.registro)) {
            return false;
        }
        if (!Objects.equals(this.ID_estadisticas, other.ID_estadisticas)) {
            return false;
        }
        if (!Objects.equals(this.puntosAnotados, other.puntosAnotados)) {
            return false;
        }
        if (!Objects.equals(this.puntosEncajados, other.puntosEncajados)) {
            return false;
        }
        if (!Objects.equals(this.victorias, other.victorias)) {
            return false;
        }
        if (!Objects.equals(this.derrotas, other.derrotas)) {
            return false;
        }
        if (!Objects.equals(this.ID_equipo, other.ID_equipo)) {
            return false;
        }
        return Objects.equals(this.ID_temporada, other.ID_temporada);
    }

    @Override
    public String toString() {
        return "EstadisticasEquipoPorTemporada{" +
                "ID_estadisticas=" + ID_estadisticas + 
                ", puntosAnotados=" + puntosAnotados + 
                ", puntosEncajados=" + puntosEncajados + 
                ", victorias=" + victorias + 
                ", derrotas=" + derrotas + 
                ", registro='" + registro + '\'' + 
                ", ID_equipo=" + ID_equipo + 
                ", ID_temporada=" + ID_temporada + 
                '}';
    }
}
