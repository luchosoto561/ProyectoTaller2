package Aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;

@SuppressWarnings("serial")
public class PanelActivos extends JPanel {
    private JButton btnCerrarSesion;
    private JButton btnGenerarDatos;
    private JButton btnExportarCSV;
    private JButton btnMisOperaciones;
    private JButton btnCotizaciones;
    private JLabel lblUsuario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private TableRowSorter<DefaultTableModel> sorter;
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

        // Botón generar datos
        btnGenerarDatos = new JButton("Generar Datos");
        btnGenerarDatos.setBounds(420, 55, 150, 30);
        btnGenerarDatos.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerarDatos.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnGenerarDatos.setForeground(Color.WHITE);
        btnGenerarDatos.setFocusPainted(false);
        btnGenerarDatos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnGenerarDatos);

     
        // **Crear la tabla dinámica**
        String[] columnas = {"", "Criptomoneda", "Monto"};
        modeloTabla = new DefaultTableModel(columnas, 0); // 0 indica que no hay filas iniciales
        tabla = new JTable(modeloTabla);
        tabla.setBackground(new Color (245, 245, 220)); //200,150,100
        tabla.setRowHeight(30);
        
        tabla.setRowSelectionAllowed(false); // No permite seleccionar filas completas
        tabla.setCellSelectionEnabled(false); // No permite seleccionar celdas individuales
        tabla.getTableHeader().setResizingAllowed(false); // Desactivar redimensionamiento
        tabla.getTableHeader().setReorderingAllowed(false); // Desactivar movimiento de columnas

        
        // Cambiar tamaño y fuente de los nombres de las columnas
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color (139, 69, 19));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        tabla.getTableHeader().setPreferredSize(new Dimension(tabla.getWidth(), 40)); // Altura 30px
        tabla.setGridColor(new Color(0, 0, 0));
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setFont(new Font("Arial", Font.BOLD, 24));
        renderer.setForeground(Color.BLACK);
        renderer.setBackground(new Color (245, 245, 220));
        renderer.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1)); 
        tabla.setDefaultRenderer(Object.class, renderer);

        // Crear el sorter para permitir el ordenamiento de las filas
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);
        
        // Agregar la tabla a un JScrollPane para que tenga scroll
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        scroll.setBounds(31, 36, 355, 330);
        
        add(scroll);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        this.agregarFila(4, "buitrecoin", 30000);
        this.agregarFila(7, "dogeperro", 34670);
        

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
    
    // Método para agregar una fila a la tabla
    public void agregarFila(int id, String criptomoneda, double precio) {
        // Crear el arreglo con los valores que se pasarán como parámetro
        Object[] nuevaFila = {id, criptomoneda, precio};
        
        // Obtener el modelo de la tabla y agregar la fila
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(nuevaFila); // Agregar la fila al modelo de la tabla
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
        return tabla;
    }
}
