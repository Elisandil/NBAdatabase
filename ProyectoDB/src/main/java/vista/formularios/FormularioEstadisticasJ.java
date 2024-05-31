package vista.formularios;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FormularioEstadisticasJ extends JPanel {
    private JTextField puntosField;
    private JTextField rebotesField;
    private JTextField asistenciasField;
    private JTextField robosField;
    private JTextField taponesField;
    private JTextField id_jugadorField;
    private JTextField id_partidoField;

    public FormularioEstadisticasJ() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        puntosField = new JTextField(10);
        rebotesField = new JTextField(10);
        asistenciasField = new JTextField(10);
        robosField = new JTextField(10);
        taponesField = new JTextField(10);
        id_jugadorField = new JTextField(10);
        id_partidoField = new JTextField(10);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createLabeledField("Puntos:", puntosField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Rebotes:", rebotesField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Asistencias:", asistenciasField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Robos:", robosField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("Tapones:", taponesField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID Jugador:", id_jugadorField));
        add(Box.createVerticalStrut(10));
        add(createLabeledField("ID Partido:", id_partidoField));
    }

    private JPanel createLabeledField(String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(field);
        return panel;
    }

    public String getPuntos() {
        return puntosField.getText();
    }

    public void setPuntos(String puntos) {
        puntosField.setText(puntos);
    }

    public String getRebotes() {
        return rebotesField.getText();
    }

    public void setRebotes(String rebotes) {
        rebotesField.setText(rebotes);
    }

    public String getAsistencias() {
        return asistenciasField.getText();
    }

    public void setAsistencias(String asistencias) {
        asistenciasField.setText(asistencias);
    }

    public String getRobos() {
        return robosField.getText();
    }

    public void setRobos(String robos) {
        robosField.setText(robos);
    }

    public String getTapones() {
        return taponesField.getText();
    }

    public void setTapones(String tapones) {
        taponesField.setText(tapones);
    }

    public String getID_jugador() {
        return id_jugadorField.getText();
    }

    public void setID_jugador(String id_jugador) {
        id_jugadorField.setText(id_jugador);
    }

    public String getID_partido() {
        return id_partidoField.getText();
    }

    public void setID_partido(String id_partido) {
        id_partidoField.setText(id_partido);
    }
}
