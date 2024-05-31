package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {
    private JPanel panel;
    private JPanel panel2;
    private JPanel panelCombobox;
    private JButton btnAdd;
    private JButton btnBorrarPorId;
    private JButton btnBuscar;
    private JButton btnVolver;
    private JButton btnActualizar;
    private JScrollPane scroll;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboBox;

    public Vista() {
        initWindow();
        initComponents();
        initLayout();
    }

    private void initWindow() {
        setUndecorated(true);
        setSize(1024, 680);
        setTitle("NBA Database");
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panel = new JPanel();
        String[] botones = {"Add", "Borrar por ID", "Buscar", "Actualizar"};
        for (String textoBoton : botones) {
            JButton button = new JButton(textoBoton);
            button.setActionCommand(textoBoton);
            switch (textoBoton) {
                case "Add" -> btnAdd = button;
                case "Borrar por ID" -> btnBorrarPorId = button;
                case "Buscar" -> btnBuscar = button;
                default -> btnActualizar = button;
            }
            panel.add(button);
        }

        panel2 = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.setActionCommand("volver");
        panel2.add(btnVolver);

        String[] tablas = {"----", "Temporadas", "Partidos", "Equipos", "Estadisticas Equipo", "Jugadores", "Estadisticas", "Premios"};
        comboBox = new JComboBox<>(tablas);
        panelCombobox = new JPanel();
        panelCombobox.add(comboBox);
        panelCombobox.setLayout(new FlowLayout(FlowLayout.LEFT));

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scroll = new JScrollPane(table);
    }

    private void initLayout() {
        JPanel eastWestPanel = new JPanel(new BorderLayout());
        eastWestPanel.add(panel, BorderLayout.WEST);
        eastWestPanel.add(panel2, BorderLayout.EAST);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setLayout(new BorderLayout());
        contentPane.add(panelCombobox, BorderLayout.NORTH);
        contentPane.add(scroll, BorderLayout.CENTER);
        contentPane.add(eastWestPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnBorrarPorId() {
        return btnBorrarPorId;
    }
    
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}