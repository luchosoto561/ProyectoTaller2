package Aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelActivos extends JPanel {
    private JButton btnCerrarSesion;
    private JButton btnGenerarDatos;
    private JButton btnExportarCSV;
    private JButton btnMisOperaciones;
    private JButton btnCotizaciones;
    private JLabel lblUsuario;
    private JTable tblCriptos;
    @SuppressWarnings("unused")
	private String Usuario;
    private int num = 0;

    public PanelActivos() {
        setLayout(null);
        setBackground(new Color(222, 184, 135)); // Fondo marroncito

        

        // Botón cerrar sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(420, 10, 150, 30);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 18));
        btnCerrarSesion.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnCerrarSesion);
        add(btnCerrarSesion);

        // Botón generar datos
        btnGenerarDatos = new JButton("Generar Datos");
        btnGenerarDatos.setBounds(420, 55, 150, 30);
        btnGenerarDatos.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerarDatos.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnGenerarDatos.setForeground(Color.WHITE);
        btnGenerarDatos.setFocusPainted(false);
        btnGenerarDatos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnGenerarDatos);

        // Tabla de activos
        String[] columnNames = {"Símbolo", "Nombre", "Monto"};
        Object[][] data = {
            {"BTC", "Bitcoin", "0.5"},
            {"ETH", "Ethereum", "2.3"},
            {"LTC", "Litecoin", "15.6"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tblCriptos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblCriptos);
        scrollPane.setBounds(20, 75, 380, 300);
        tblCriptos.setFont(new Font("Arial", Font.BOLD, 14));
        tblCriptos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        add(scrollPane);

        // Botón Exportar como CSV
        btnExportarCSV = new JButton("Exportar como CSV");
        btnExportarCSV.setBounds(420, 100, 150, 30);
        btnExportarCSV.setFont(new Font("Arial", Font.BOLD, 14));
        btnExportarCSV.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnExportarCSV.setForeground(Color.WHITE);
        btnExportarCSV.setFocusPainted(false);
        btnExportarCSV.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnExportarCSV);

        // Botones de navegación
        btnMisOperaciones = new JButton("Mis Operaciones");
        btnMisOperaciones.setBounds(45, 400, 150, 40);
        btnMisOperaciones.setFont(new Font("Arial", Font.BOLD, 14));
        btnMisOperaciones.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnMisOperaciones.setForeground(Color.WHITE);
        btnMisOperaciones.setFocusPainted(false);
        btnMisOperaciones.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnMisOperaciones);

        btnCotizaciones = new JButton("Cotizaciones");
        btnCotizaciones.setBounds(215, 400, 150, 40);
        btnCotizaciones.setFont(new Font("Arial", Font.BOLD, 14));
        btnCotizaciones.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnCotizaciones.setForeground(Color.WHITE);
        btnCotizaciones.setFocusPainted(false);
        btnCotizaciones.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnCotizaciones);
    }

    public void nombreUsuario (String Usuario) {
    	this.Usuario = Usuario;
    	// Etiqueta para el usuario
    	if (num != 0) remove (lblUsuario);
        lblUsuario = new JLabel(("Usuario: " + Usuario));
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 30));
        lblUsuario.setForeground(new Color(40, 40, 40)); // Negro suave
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBounds(60, 25, 300, 30);
        add(lblUsuario);
        num++;
    }
    
    // Métodos get de los atributos
    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JButton getBtnGenerarDatos() {
        return btnGenerarDatos;
    }

    public JButton getBtnExportarCSV() {
        return btnExportarCSV;
    }

    public JButton getBtnMisOperaciones() {
        return btnMisOperaciones;
    }

    public JButton getBtnCotizaciones() {
        return btnCotizaciones;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JTable getTblCriptos() {
        return tblCriptos;
    }
}
