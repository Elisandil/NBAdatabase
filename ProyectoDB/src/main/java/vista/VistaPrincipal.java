package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class VistaPrincipal extends JFrame {
    private JButton cargarDatosButton;
    private JButton salirButton;

    public VistaPrincipal() {
        super("NBA Database");
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("src/main/java/util/NBAlogo.png");
        JLabel logoLabel = new JLabel(logo);
        panel.add(logoLabel, BorderLayout.CENTER);

        JPanel cargarDatosPanel = new JPanel();
        cargarDatosPanel.setLayout(new BorderLayout());
        cargarDatosButton = new JButton("Cargar Tablas");
        cargarDatosPanel.add(cargarDatosButton, BorderLayout.CENTER);

        JPanel salirPanel = new JPanel();
        salirPanel.setLayout(new BorderLayout());
        salirButton = new JButton("Salir");
        salirPanel.add(salirButton, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(cargarDatosPanel);
        buttonsPanel.add(salirPanel);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public JButton getCargarDatosButton() {
        return cargarDatosButton;
    }
    
    public JButton getSalirButton() {
        return salirButton;
    }
}
