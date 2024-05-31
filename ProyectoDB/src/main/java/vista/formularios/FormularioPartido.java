package vista.formularios;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioPartido extends JPanel {
    private JTextField fechaPartidoField;
    private JTextField equipoLocalField;
    private JTextField equipoVisitanteField;
    private JTextField puntuacionLocalField;
    private JTextField puntuacionVisitanteField;
    private JTextField id_temporadaField;

    public FormularioPartido() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        fechaPartidoField = new JTextField(10);
        equipoLocalField = new JTextField(20);
        equipoVisitanteField = new JTextField(20);
        puntuacionLocalField = new JTextField(10);
        puntuacionVisitanteField = new JTextField(10);
        id_temporadaField = new JTextField(10);
    }

    private void layoutComponents() {
        setLayout(new GridLayout(6, 2));
        add(new JLabel("Fecha (YYYY-MM-DD):"));
        add(fechaPartidoField);
        add(new JLabel("Equipo Local:"));
        add(equipoLocalField);
        add(new JLabel("Equipo Visitante:"));
        add(equipoVisitanteField);
        add(new JLabel("Puntuación Local:"));
        add(puntuacionLocalField);
        add(new JLabel("Puntuación Visitante:"));
        add(puntuacionVisitanteField);
        add(new JLabel("ID Temporada:"));
        add(id_temporadaField);
    }

    public String getFechaPartido() {
        return fechaPartidoField.getText();
    }

    public void setFechaPartido(String fechaPartido) {
        fechaPartidoField.setText(fechaPartido);
    }

    public String getEquipoLocal() {
        return equipoLocalField.getText();
    }

    public void setEquipoLocal(String equipoLocal) {
        equipoLocalField.setText(equipoLocal);
    }

    public String getEquipoVisitante() {
        return equipoVisitanteField.getText();
    }

    public void setEquipoVisitante(String equipoVisitante) {
        equipoVisitanteField.setText(equipoVisitante);
    }

    public String getPuntuacionLocal() {
        return puntuacionLocalField.getText();
    }

    public void setPuntuacionLocal(String puntuacionLocal) {
        puntuacionLocalField.setText(puntuacionLocal);
    }

    public String getPuntuacionVisitante() {
        return puntuacionVisitanteField.getText();
    }

    public void setPuntuacionVisitante(String puntuacionVisitante) {
        puntuacionVisitanteField.setText(puntuacionVisitante);
    }

    public String getID_temporada() {
        return id_temporadaField.getText();
    }

    public void setID_temporada(String id_temporada) {
        id_temporadaField.setText(id_temporada);
    }
}
