package vista.formularios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioEquipo extends JPanel {
    private JTextField nombreField;
    private JTextField ciudadField;
    private JComboBox<String> divisionComboBox;
    private JComboBox<String> conferenciaComboBox;
    private JTextField estadioField;
    private JTextField entrenadorField;

    public FormularioEquipo() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        nombreField = new JTextField(20);
        ciudadField = new JTextField(20);

        conferenciaComboBox = new JComboBox<>(new String[]{"Este", "Oeste"});
        divisionComboBox = new JComboBox<>();

        estadioField = new JTextField(20);
        entrenadorField = new JTextField(20);

        updateDivisionComboBox("Este");
        conferenciaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedConference = (String) conferenciaComboBox.getSelectedItem();
                updateDivisionComboBox(selectedConference);
            }
        });
    }

    private void updateDivisionComboBox(String conference) {
        divisionComboBox.removeAllItems();
        if ("Este".equals(conference)) {
            divisionComboBox.addItem("Atlántico");
            divisionComboBox.addItem("Central");
            divisionComboBox.addItem("Sureste");
        } else if ("Oeste".equals(conference)) {
            divisionComboBox.addItem("Noroeste");
            divisionComboBox.addItem("Suroeste");
            divisionComboBox.addItem("Pacífico");
        }
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(createLabeledField("Nombre:", nombreField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Ciudad:", ciudadField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Conferencia:", conferenciaComboBox));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("División:", divisionComboBox));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Estadio:", estadioField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Entrenador:", entrenadorField));
    }

    private JPanel createLabeledField(String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(field);
        return panel;
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public void setNombre(String nombre) {
        nombreField.setText(nombre);
    }

    public String getCiudad() {
        return ciudadField.getText();
    }

    public void setCiudad(String ciudad) {
        ciudadField.setText(ciudad);
    }

    public String getDivision() {
        return (String) divisionComboBox.getSelectedItem();
    }

    public void setDivision(String division) {
        divisionComboBox.setSelectedItem(division);
    }

    public String getConferencia() {
        return (String) conferenciaComboBox.getSelectedItem();
    }

    public void setConferencia(String conferencia) {
        conferenciaComboBox.setSelectedItem(conferencia);
    }

    public String getEstadio() {
        return estadioField.getText();
    }

    public void setEstadio(String estadio) {
        estadioField.setText(estadio);
    }

    public String getEntrenador() {
        return entrenadorField.getText();
    }

    public void setEntrenador(String entrenador) {
        entrenadorField.setText(entrenador);
    }
}
