package vista.formularios;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioEstadisticasE extends JPanel {
    private JTextField puntosAnotadosField;
    private JTextField puntosEncajadosField;
    private JTextField victoriasField;
    private JTextField derrotasField;
    private JTextField registroField;
    private JTextField idEquipoField;
    private JTextField idTemporadaField;

    public FormularioEstadisticasE() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        puntosAnotadosField = new JTextField(10);
        puntosEncajadosField = new JTextField(10);
        victoriasField = new JTextField(10);
        derrotasField = new JTextField(10);
        registroField = new JTextField(10);
        idEquipoField = new JTextField(10);
        idTemporadaField = new JTextField(10);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(createLabeledField("Puntos Anotados:", puntosAnotadosField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Puntos Encajados:", puntosEncajadosField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Victorias:", victoriasField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Derrotas:", derrotasField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Registro:", registroField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID Equipo:", idEquipoField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID Temporada:", idTemporadaField));
    }

    private JPanel createLabeledField(String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(field);
        return panel;
    }

    public String getPuntosAnotados() {
        return puntosAnotadosField.getText();
    }

    public void setPuntosAnotados(String puntosAnotados) {
        puntosAnotadosField.setText(puntosAnotados);
    }

    public String getPuntosEncajados() {
        return puntosEncajadosField.getText();
    }

    public void setPuntosEncajados(String puntosEncajados) {
        puntosEncajadosField.setText(puntosEncajados);
    }

    public String getVictorias() {
        return victoriasField.getText();
    }

    public void setVictorias(String victorias) {
        victoriasField.setText(victorias);
    }

    public String getDerrotas() {
        return derrotasField.getText();
    }

    public void setDerrotas(String derrotas) {
        derrotasField.setText(derrotas);
    }

    public String getRegistro() {
        return registroField.getText();
    }

    public void setRegistro(String registro) {
        registroField.setText(registro);
    }

    public String getIDEquipo() {
        return idEquipoField.getText();
    }

    public void setIDEquipo(String idEquipo) {
        idEquipoField.setText(idEquipo);
    }

    public String getIDTemporada() {
        return idTemporadaField.getText();
    }

    public void setIDTemporada(String idTemporada) {
        idTemporadaField.setText(idTemporada);
    }   
}
