package Aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelActivos extends JPanel {

    private JButton btnCerrarSesion;
    private JButton btnGenerarDatos;
    private JButton btnExportarCSV;
    private JButton btnMisOperaciones;
    private JButton btnCotizaciones;
    private JLabel lblUsuario;
    private JTable tblCriptos;

    public PanelActivos() {
        setLayout(null);

        // Botón cerrar sesión (arriba derecha)
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(350, 10, 120, 30);
        add(btnCerrarSesion);

        // Botón generar datos de prueba (debajo del botón de cerrar sesión)
        btnGenerarDatos = new JButton("Generar Datos de Prueba");
        btnGenerarDatos.setBounds(350, 50, 180, 30);
        add(btnGenerarDatos);

        // Texto de usuario (arriba, debajo del botón de cerrar sesión)
        lblUsuario = new JLabel("Luciano");
        lblUsuario.setBounds(350, 90, 100, 30);
        add(lblUsuario);

        // Tabla con tres campos: símbolo, nombre y monto
        String[] columnNames = {"Símbolo", "Nombre", "Monto"};
        Object[][] data = {
            {"BTC", "Bitcoin", "0.5"},
            {"ETH", "Ethereum", "2.3"},
            {"LTC", "Litecoin", "15.6"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tblCriptos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblCriptos);
        scrollPane.setBounds(50, 50, 250, 200);
        add(scrollPane);

        // Botones Cripto y Monto en la parte superior de la tabla
        JButton btnCripto = new JButton("Cripto");
        btnCripto.setBounds(50, 10, 80, 30);
        add(btnCripto);

        JButton btnMonto = new JButton("Monto");
        btnMonto.setBounds(180, 10, 80, 30);
        add(btnMonto);

        // Botón Exportar como CSV
        btnExportarCSV = new JButton("Exportar como CSV");
        btnExportarCSV.setBounds(50, 270, 200, 30);
        add(btnExportarCSV);

        // Botones Mis Operaciones y Cotizaciones
        btnMisOperaciones = new JButton("Mis Operaciones");
        btnMisOperaciones.setBounds(50, 310, 150, 30);
        add(btnMisOperaciones);

        btnCotizaciones = new JButton("Cotizaciones");
        btnCotizaciones.setBounds(210, 310, 150, 30);
        add(btnCotizaciones);
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
