package Aplicacion;

import javax.swing.*;

import java.awt.*;
@SuppressWarnings("serial")
public class PanelMisOperaciones extends JPanel {
	private JButton btnCerrarSesion;
    private JList<String> lista; // Lista para mostrar las operaciones
    private DefaultListModel<String> modeloLista; // Modelo para gestionar los elementos de la lista
    private JButton btnVolver; // Botón para volver
    
    public PanelMisOperaciones() {
    	
    	// Configuración del panel
    	setLayout(null);
    	setBackground(new Color(222, 184, 135)); // Marroncito
    	
        // Título del panel
        JLabel lblTitulo = new JLabel("Lista de Operaciones");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setOpaque(true);
        lblTitulo.setBounds(100, 70, 390, 33);
        lblTitulo.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setVerticalAlignment(SwingConstants.CENTER);
        add(lblTitulo);
        
        
     // Crear el modelo de la lista
        @SuppressWarnings("unused")
		String[] columnas = {"ID", "Criptomoneda", "Precio"};
        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        lista.setBackground(new Color(245, 245, 220)); //200,150,100
        lista.setFont(new Font("Arial", Font.BOLD, 14));
        lista.setForeground(Color.BLACK);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        
        // Agregar la lista a un JScrollPane para permitir el scroll
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        scroll.setBounds(100, 103, 390, 220);
        
        add(scroll);
        
        
        // Botón Volver
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(210, 350, 150, 40);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnVolver);
        
        // Botón cerrar sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(420, 10, 150, 30);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 18));
        btnCerrarSesion.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnCerrarSesion);
    }
    // Métodos get para los componentes
    public JList<String> getListaOperaciones() {
        return lista;
    }
    public void agregarCriptomoneda(String fecha, String hora, String tipo, String cantidad) {
        modeloLista.addElement(fecha + " - " + hora + " - " + tipo + " - " + cantidad);
    }
    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
    public JButton getBtnVolver() {
        return btnVolver;
    }
    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
