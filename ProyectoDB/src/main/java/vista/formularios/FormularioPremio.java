package vista.formularios;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioPremio extends JPanel {
    private JTextField tipoField;
    private JTextField descripcionField;
    private JTextField votacionField;
    private JComboBox<String> categoriaComboBox;
    private JComboBox<String> statusComboBox;
    private JTextField temporadaIdField;

    public FormularioPremio() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        tipoField = new JTextField(20);
        descripcionField = new JTextField(20);
        votacionField = new JTextField(20);
        categoriaComboBox = new JComboBox<>(new String[]{"Individual", "Colectivo"});
        statusComboBox = new JComboBox<>(new String[]{"No entregado", "Entregado"});
        temporadaIdField = new JTextField(20);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createLabeledField("Tipo de Premio:", tipoField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Descripción:", descripcionField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Votación:", votacionField));
        add(Box.createVerticalStrut(10));
        add(createLabeledComboBox("Categoría:", categoriaComboBox));
        add(Box.createVerticalStrut(10));
        add(createLabeledComboBox("Estado del Premio:", statusComboBox));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID de la Temporada:", temporadaIdField));
    }

    private JPanel createLabeledField(String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(field);
        return panel;
    }

    private JPanel createLabeledComboBox(String label, JComboBox<String> comboBox) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(comboBox);
        return panel;
    }

    public String getTipo() {
        return tipoField.getText();
    }

    public String getDescripcion() {
        return descripcionField.getText();
    }

    public String getVotacion() {
        return votacionField.getText();
    }

    public String getCategoria() {
        return (String) categoriaComboBox.getSelectedItem();
    }

    public String getStatus() {
        return (String) statusComboBox.getSelectedItem();
    }

    public Integer getTemporadaId() {
        try {
            return Integer.valueOf(temporadaIdField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public void setTipo(String tipo) {
        tipoField.setText(tipo);
    }

    public void setDescripcion(String descripcion) {
        descripcionField.setText(descripcion);
    }

    public void setVotacion(String votacion) {
        votacionField.setText(votacion);
    }

    public void setCategoria(String categoria) {
        categoriaComboBox.setSelectedItem(categoria);
    }

    public void setStatus(String status) {
        statusComboBox.setSelectedItem(status);
    }

    public void setTemporadaId(Integer temporadaId) {
        temporadaIdField.setText(String.valueOf(temporadaId));
    }    
}
