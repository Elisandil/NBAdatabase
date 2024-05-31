package vista.formularios;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormularioTemporada extends JPanel {
    private JTextField anioField;
    private JComboBox<String> tipoComboBox;
    private JComboBox<String> estadoComboBox;
    private JTextArea descripcionField;

    public FormularioTemporada() {
        setLayout(new GridLayout(4, 2, 5, 5));

        JLabel lblAnio = new JLabel("Año:");
        anioField = new JTextField();
        JLabel lblTipo = new JLabel("Tipo:");
        tipoComboBox = new JComboBox<>(new String[]{"Regular", "Playoffs"});
        JLabel lblEstado = new JLabel("Estado:");
        estadoComboBox = new JComboBox<>(new String[]{"No iniciada", "Finalizada", "En curso"});
        JLabel lblDescripcion = new JLabel("Descripción:");
        descripcionField = new JTextArea();

        add(lblAnio);
        add(anioField);
        add(lblTipo);
        add(tipoComboBox);
        add(lblEstado);
        add(estadoComboBox);
        add(lblDescripcion);
        add(new JScrollPane(descripcionField));
    }

    public Integer getAnio() {
        try {
            return Integer.valueOf(anioField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void setAnio(Integer anio) {
        anioField.setText(anio != null ? anio.toString() : "");
    }

    public String getTipo() {
        return (String) tipoComboBox.getSelectedItem();
    }

    public void setTipo(String tipo) {
        tipoComboBox.setSelectedItem(tipo);
    }

    public String getEstado() {
        return (String) estadoComboBox.getSelectedItem();
    }

    public void setEstado(String estado) {
        estadoComboBox.setSelectedItem(estado);
    }

    public String getDescripcion() {
        return descripcionField.getText();
    }

    public void setDescripcion(String descripcion) {
        descripcionField.setText(descripcion);
    }
}
