package modelo;

import java.util.Objects;

public class Temporada {
    private Integer id_temporada;
    private Integer anio;
    private String tipo;
    private String estado;
    private String descripcion;

    public Temporada(Integer id_temporada, Integer anio, String tipo, String estado, String descripcion) {
        this.id_temporada = id_temporada;
        this.anio = anio;
        this.tipo = tipo;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Integer getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(Integer id_temporada) {
        this.id_temporada = id_temporada;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_temporada);
        hash = 97 * hash + Objects.hashCode(this.anio);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + Objects.hashCode(this.estado);
        hash = 97 * hash + Objects.hashCode(this.descripcion);
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
        final Temporada other = (Temporada) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id_temporada, other.id_temporada)) {
            return false;
        }
        return Objects.equals(this.anio, other.anio);
    }

    @Override
    public String toString() {
        return "Temporada{" + 
                "id_temporada=" + id_temporada + 
                ", año=" + anio + 
                ", tipo=" + tipo + 
                ", estado=" + estado + 
                ", descripcion=" + descripcion + 
                '}';
    }   
}
