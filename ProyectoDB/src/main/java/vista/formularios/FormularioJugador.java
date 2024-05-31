package vista.formularios;

import javax.swing.*;

public class FormularioJugador extends JPanel {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JComboBox<String> posicionComboBox;
    private JTextField alturaField;
    private JTextField pesoField;
    private JTextField fechaNacimientoField;
    private JTextField id_equipoField;

    public FormularioJugador() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        posicionComboBox = new JComboBox<>(new String[]{"Base", "Escolta", "Alero", "Ala-Pivot", "Pivot"});
        alturaField = new JTextField(10);
        pesoField = new JTextField(10);
        fechaNacimientoField = new JTextField(10);
        id_equipoField = new JTextField();
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(createLabeledField("Nombre:", nombreField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Apellido:", apellidoField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Posición:", posicionComboBox));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Altura:", alturaField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Peso:", pesoField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Fecha de nacimiento (YYYY-MM-DD):", fechaNacimientoField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID_equipo:", id_equipoField));
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

    public String getApellido() {
        return apellidoField.getText();
    }

    public void setApellido(String apellido) {
        apellidoField.setText(apellido);
    }

    public String getPosicion() {
        return (String) posicionComboBox.getSelectedItem();
    }

    public void setPosicion(String posicion) {
        posicionComboBox.setSelectedItem(posicion);
    }

    public double getAltura() {
        return Double.parseDouble(alturaField.getText());
    }

    public void setAltura(String altura) {
        alturaField.setText(altura);
    }

    public double getPeso() {
        return Double.parseDouble(pesoField.getText());
    }

    public void setPeso(String peso) {
        pesoField.setText(peso);
    }

    public String getFechaNacimiento() {
        return fechaNacimientoField.getText();
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        fechaNacimientoField.setText(fechaNacimiento);
    }

    public Integer getID_equipo() {
        return Integer.valueOf(id_equipoField.getText());
    }

    public void setID_equipo(String id_equipo) {
        id_equipoField.setText(id_equipo);
    }
}
