package modelo;

import java.util.Objects;

public class Premio {
    private Integer ID_premio;
    private String tipoPremio;
    private String descripcion;
    private String votacion;
    private String categoria;
    private String statusPremio;
    private Integer ID_temporada;

    public Premio(Integer ID_premio, String tipoPremio, String descripcion, String votacion, String categoria, String statusPremio, Integer ID_temporada) {
        this.ID_premio = ID_premio;
        this.tipoPremio = tipoPremio;
        this.descripcion = descripcion;
        this.votacion = votacion;
        this.categoria = categoria;
        this.statusPremio = statusPremio;
        this.ID_temporada = ID_temporada;
    }

    public Integer getID_premio() {
        return ID_premio;
    }

    public void setID_premio(Integer ID_premio) {
        this.ID_premio = ID_premio;
    }

    public String getTipoPremio() {
        return tipoPremio;
    }

    public void setTipoPremio(String tipoPremio) {
        this.tipoPremio = tipoPremio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVotacion() {
        return votacion;
    }

    public void setVotacion(String votacion) {
        this.votacion = votacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatusPremio() {
        return statusPremio;
    }

    public void setStatusPremio(String statusPremio) {
        this.statusPremio = statusPremio;
    }

    public Integer getID_temporada() {
        return ID_temporada;
    }

    public void setID_temporada(Integer ID_temporada) {
        this.ID_temporada = ID_temporada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.ID_premio);
        hash = 17 * hash + Objects.hashCode(this.tipoPremio);
        hash = 17 * hash + Objects.hashCode(this.descripcion);
        hash = 17 * hash + Objects.hashCode(this.votacion);
        hash = 17 * hash + Objects.hashCode(this.categoria);
        hash = 17 * hash + Objects.hashCode(this.statusPremio);
        hash = 17 * hash + Objects.hashCode(this.ID_temporada);
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
        final Premio other = (Premio) obj;
        if (!Objects.equals(this.tipoPremio, other.tipoPremio)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.votacion, other.votacion)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.statusPremio, other.statusPremio)) {
            return false;
        }
        if (!Objects.equals(this.ID_premio, other.ID_premio)) {
            return false;
        }
        return Objects.equals(this.ID_temporada, other.ID_temporada);
    }

    @Override
    public String toString() {
        return "Premio{" + 
                "ID_premio=" + ID_premio + 
                ", tipoPremio=" + tipoPremio + 
                ", descripcion=" + descripcion + 
                ", votacion=" + votacion + 
                ", categoria=" + categoria + 
                ", statusPremio=" + statusPremio + 
                ", ID_temporada=" + ID_temporada + 
                '}';
    }
}
